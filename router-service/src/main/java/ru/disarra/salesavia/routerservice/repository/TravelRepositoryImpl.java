package ru.disarra.salesavia.routerservice.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
@Primary
public class TravelRepositoryImpl implements TravelRepository{
    private final Neo4jClient neo4jClient;
    private final String createTravelQuery =
            "MATCH (a:Airport), (b:Airport) " +
                    "WHERE a.name=$airportNameA AND b.name=$airportNameB " +
                    "CREATE (a)-[r:Travel {arrival: $arrival, departure: $departure, price: $price}]->(b) " +
                    "RETURN r";

    public TravelRepositoryImpl(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @Override
    public void create(String airportNameA, String airportNameB, ZonedDateTime arrival, ZonedDateTime departure, int price) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("airportNameA", airportNameA);
        parameters.put("airportNameB", airportNameB);
        parameters.put("arrival", arrival);
        parameters.put("departure", departure);
        parameters.put("price", price);

        neo4jClient
                .query(createTravelQuery)
                .bindAll(parameters)
                .run();
    }
}
