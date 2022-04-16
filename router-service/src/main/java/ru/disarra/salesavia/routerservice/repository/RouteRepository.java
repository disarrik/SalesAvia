package ru.disarra.salesavia.routerservice.repository;

import ru.disarra.salesavia.routerservice.dto.RouteDTO;

import java.time.ZonedDateTime;
import java.util.Collection;


public interface RouteRepository {
    Collection<RouteDTO> getRoutes(String cityA, String cityB, int transfers, int hoursInTravel, ZonedDateTime travelTime);
}
