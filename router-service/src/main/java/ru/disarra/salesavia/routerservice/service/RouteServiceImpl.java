package ru.disarra.salesavia.routerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.disarra.salesavia.routerservice.dto.RouteDTO;
import ru.disarra.salesavia.routerservice.repository.RouteRepository;

import java.time.ZonedDateTime;
import java.util.Collection;

@Service
@Primary
public class RouteServiceImpl implements RouteService{

    private final RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Collection<RouteDTO> getRoutes(String cityA, String cityB, int transfers, int hoursInTravel, ZonedDateTime travelTime) {
        return routeRepository.getRoutes(cityA, cityB, transfers, hoursInTravel, travelTime);
    }
}
