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

import java.util.*;

@Service
@Primary
public class RouteServiceImpl implements RouteService {

    private final Neo4jClient neo4jClient;

    @Autowired
    public RouteServiceImpl(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @Override
    public Collection<RouteDTO> getRoutesByCitiesAndTransfersLimit(String cityA, String cityB, int transfers) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("cityA", cityA);
        parameters.put("cityB", cityB);
        parameters.put("cities", transfers+2);

        return neo4jClient
                .query(
                "MATCH p=((a)-[t:Travel*]->(b)) " +
                        "WHERE a.city=$cityA AND b.city=$cityA AND length(p)<$cities " +
                        "RETURN nodes(p), " +
                        "relationships(p), " +
                        "reduce(totalprice = 0, trip IN relationships(p) | totalprice + toInteger(trip.price)) AS price, " +
                        "reduce(totaltime = duration('P0DT0H0M'), trip IN relationships(p) | " +
                        "totaltime + (duration.inSeconds(datetime(trip.arrival), datetime(trip.departure))) " +
                        ") AS time " +
                        "ORDER BY time DESC , price DESC ")
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
