package ru.disarra.salesavia.routerservice.service;

import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.internal.InternalRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;
import ru.disarra.salesavia.routerservice.dto.AirportDTO;
import ru.disarra.salesavia.routerservice.dto.RouteDTO;
import ru.disarra.salesavia.routerservice.dto.TravelDTO;

import java.time.ZonedDateTime;
import java.util.*;

@Service
@Primary
public class RouteServiceImpl implements RouteService {

    private final Neo4jClient neo4jClient;
    private final String getRoutesQuery =
            "MATCH p=((a)-[t:Travel*]->(b)) " +
            "WHERE a.city=$cityA AND b.city=$cityB AND  length(p) < $cities AND " +
            "reduce(totaltime = duration('P0DT0H0M'), trip IN relationships(p) | " +
            "        totaltime + (duration.inSeconds(datetime(trip.departure), datetime(trip.arrival))) " +
            "    ).hours  < $hoursInTravel AND " +
            "datetime() + duration.inSeconds(datetime(t[0].departure), datetime($travelDateTime)) <  datetime() AND " +
            "datetime() + duration.inSeconds(datetime(t[0].departure), datetime($travelDateTime)) < datetime() + duration({days: 1}) " +
            "RETURN " +
            "nodes(p), " +
            "relationships(p), " +
            "reduce(totalprice = 0, trip IN relationships(p) | totalprice + toInteger(trip.price)) AS price, " +
            "reduce(totaltime = duration('P0DT0H0M'), trip IN relationships(p) | " +
            "        totaltime + (duration.inSeconds(datetime(trip.departure), datetime(trip.arrival))) " +
            "    ) as time " +
            "ORDER BY price, time DESC";

    @Autowired
    public RouteServiceImpl(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @Override
    public Collection<RouteDTO> getRoutes(String cityA, String cityB, int transfers, int hoursInTravel, ZonedDateTime travelDateTime) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("cityA", cityA);
        parameters.put("cityB", cityB);
        parameters.put("cities", transfers+2);
        parameters.put("hoursInTravel", hoursInTravel);
        parameters.put("travelDateTime", travelDateTime);

        return neo4jClient
                .query(getRoutesQuery)
                .bindAll(parameters)
                .fetchAs(RouteDTO.class)
                .mappedBy((typeSystem, record) -> {
                    List<AirportDTO> airportDTOs = new ArrayList<>();
                    for (var airport : record.get(0).asList()) {
                        airportDTOs.add(
                                new AirportDTO(
                                        ((InternalNode) airport).get("city").toString(),
                                        ((InternalNode) airport).get("name").toString()
                        ));
                    }
                    List<TravelDTO> travelDTOs = new ArrayList<>();
                    for (var travel : record.get(1).asList()) {
                        travelDTOs.add(
                                new TravelDTO(
                                        ((InternalRelationship) travel).get("price").toString(),
                                        ((InternalRelationship) travel).get("arrival").toString(),
                                        ((InternalRelationship) travel).get("departure").toString()
                                ));
                    }
                    return new RouteDTO(airportDTOs, travelDTOs);
                })
                .all();
    }
}
