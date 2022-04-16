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
import java.util.function.BiFunction;

@Component
public class RouteDTOMapper implements BiFunction<TypeSystem, Record, RouteDTO> {
    @Override
    public RouteDTO apply(TypeSystem typeSystem, Record record) {
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
    }
}
