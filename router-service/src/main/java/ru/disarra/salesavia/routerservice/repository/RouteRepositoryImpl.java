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
    private final String getRoutesQuery = "CALL route($cityA, $cityB, $hoursInTravel, $travelDateTime, $transfers)";

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
        parameters.put("transfers", transfers);
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
