package ru.disarra.salesavia.routerservice.dto.mapper;

import org.neo4j.driver.Record;
import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.internal.InternalRelationship;
import org.neo4j.driver.types.TypeSystem;
import org.springframework.stereotype.Component;
import ru.disarra.salesavia.routerservice.dto.AirportDTO;
import ru.disarra.salesavia.routerservice.dto.RouteDTO;
import ru.disarra.salesavia.routerservice.dto.TravelDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@Component
public class RouteDTOMapper implements BiFunction<TypeSystem, Record, RouteDTO> {
    @Override
    public RouteDTO apply(TypeSystem typeSystem, Record record) {
        List<AirportDTO> airportDTOs = new ArrayList<>();
        for (var airport : record.get(0).asList()) {
            airportDTOs.add(
                    new AirportDTO(
                            ((Map) airport).get("city").toString(),
                            ((Map) airport).get("name").toString()
                    ));
        }
        List<TravelDTO> travelDTOs = new ArrayList<>();
        for (var travel : record.get(1).asList()) {
            travelDTOs.add(
                    new TravelDTO(
                            ((Map) travel).get("price").toString(),
                            ((Map) travel).get("arrival").toString(),
                            ((Map) travel).get("departure").toString()
                    ));
        }
        int price = record.get(2).asInt();
        return new RouteDTO(airportDTOs, travelDTOs, price);
    }
}
