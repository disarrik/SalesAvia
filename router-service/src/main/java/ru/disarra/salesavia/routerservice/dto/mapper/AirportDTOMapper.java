package ru.disarra.salesavia.routerservice.dto.mapper;

import org.neo4j.driver.Record;
import org.neo4j.driver.types.TypeSystem;
import org.springframework.stereotype.Component;
import ru.disarra.salesavia.routerservice.dto.AirportDTO;

import java.util.function.BiFunction;

@Component
public class AirportDTOMapper implements BiFunction<TypeSystem, Record, AirportDTO> {
    @Override
    public AirportDTO apply(TypeSystem typeSystem, Record record) {
        return new AirportDTO(record.get("city").toString(), record.get("name").toString());
    }
}
