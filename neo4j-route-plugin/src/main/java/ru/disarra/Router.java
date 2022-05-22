package ru.disarra;

import org.neo4j.graphdb.*;
import org.neo4j.logging.Log;
import org.neo4j.procedure.Context;
import org.neo4j.procedure.Mode;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.Procedure;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Router
{
    @Context
    public Transaction tx;

    @Context
    public Log log;

    @Procedure(value = "route", mode = Mode.READ)
    public Stream<Route> route(@Name("cityA") String cityA,
                               @Name("cityB") String cityB,
                               @Name("hoursInTravel") Long hoursInTravel,
                               @Name("departure") ZonedDateTime departure,
                               @Name("transfers") Long transfers) {

        List<Node> departureAirports =  getAirportsInCity(cityA);
        Set<Node> arrivalAirports = new HashSet<>(getAirportsInCity(cityB));

        long citiesAmount = transfers + 2;
        List<Route> routes = new ArrayList<>();

        for (Node airport : departureAirports) {
            routes.addAll(
                    recursive(
                            airport,
                            arrivalAirports,
                            citiesAmount,
                            departure,
                            departure.plusHours(hoursInTravel))
            );
        }

        return routes.stream();
    }

    private List<Route> recursive(
            Node from,
            Set<Node> to,
            long citiesAmount,
            ZonedDateTime startAfter,
            ZonedDateTime finishBefore) {

        if (to.contains(from)) {
            return getListOfSingleAirport(from);
        }
        if (citiesAmount == 1)
            return List.of();

        List<Route> routes = new ArrayList<>();
        for(Relationship travel :  from.getRelationships(Direction.OUTGOING, RelationshipType.withName("Travel"))) {
            if (ZonedDateTime.parse(travel.getProperty("departure").toString()).isBefore(startAfter) ||
                    ZonedDateTime.parse(travel.getProperty("arrival").toString()).isAfter(finishBefore))
                continue;
            List<Route> routesAfterCurrentTravel = recursive(
                    travel.getEndNode(),
                    to,
                    citiesAmount-1,
                    ZonedDateTime.parse(travel.getProperty("arrival").toString()),
                    finishBefore
            );
            for (Route route : routesAfterCurrentTravel) {
                route.airports.add(0, buildAirportMap(from));
                route.travels.add(0, buildTravelMap(travel));
                route.price += Long.parseLong(travel.getProperty("price").toString());
            }
            routes.addAll(routesAfterCurrentTravel);
        }
        return routes;
    }

    private List<Node> getAirportsInCity(String city) {
        return tx
                .findNodes(Label.label("Airport"))
                .stream()
                .filter(
                        a -> a.getProperty("city").equals(city)
                )
                .collect(Collectors.toList());
    }

    private List<Route> getListOfSingleAirport(Node airportNode) {
        Route route = new Route();
        route.airports.add(
                buildAirportMap(airportNode)
        );
        return List.of(route);
    }

    private Map<String, Object> buildAirportMap(Node node) {
        return Map.of(
                "name", node.getProperty("name").toString(),
                "city", node.getProperty("city").toString()
        );
    }

    private Map<String, Object> buildTravelMap(Relationship relationship) {
        return Map.of(
                "airportA", relationship.getStartNode().getProperty("name").toString(),
                "airportB", relationship.getEndNode().getProperty("name").toString(),
                "price", Long.parseLong(relationship.getProperty("price").toString()),
                "departure", ZonedDateTime.parse(relationship.getProperty("departure").toString()),
                "arrival", ZonedDateTime.parse(relationship.getProperty("arrival").toString())
        );
    }

}
