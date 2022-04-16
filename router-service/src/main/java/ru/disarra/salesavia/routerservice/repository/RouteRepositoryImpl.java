package ru.disarra.salesavia.routerservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Repository;
import ru.disarra.salesavia.routerservice.dto.RouteDTO;
import ru.disarra.salesavia.routerservice.dto.mapper.RouteDTOMapper;

import java.time.ZonedDateTime;
import java.util.*;

@Repository
@Primary
public class RouteRepositoryImpl implements RouteRepository {

    private final Neo4jClient neo4jClient;
    private final RouteDTOMapper routeDTOMapper;
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
    public RouteRepositoryImpl(Neo4jClient neo4jClient, RouteDTOMapper routeDTOMapper) {
        this.neo4jClient = neo4jClient;
        this.routeDTOMapper = routeDTOMapper;
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
                .mappedBy(routeDTOMapper)
                .all();
    }
}
