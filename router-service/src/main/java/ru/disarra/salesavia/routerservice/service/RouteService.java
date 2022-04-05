package ru.disarra.salesavia.routerservice.service;

import ru.disarra.salesavia.routerservice.dto.RouteDTO;

import java.time.ZonedDateTime;
import java.util.Collection;


public interface RouteService{
    Collection<RouteDTO> getRoutes(String cityA, String cityB, int transfers, int hoursInTravel, ZonedDateTime travelTime);
}
