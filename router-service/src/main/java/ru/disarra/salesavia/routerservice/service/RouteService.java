package ru.disarra.salesavia.routerservice.service;

import ru.disarra.salesavia.routerservice.dto.RouteDTO;

import java.util.Collection;


public interface RouteService{
    Collection<RouteDTO> getRoutesByCitiesAndTransfersLimit(String airportA, String airportB, int transfers);
}
