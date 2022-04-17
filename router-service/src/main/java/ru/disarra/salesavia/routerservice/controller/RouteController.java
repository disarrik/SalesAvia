package ru.disarra.salesavia.routerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.disarra.salesavia.routerservice.dto.RouteDTO;
import ru.disarra.salesavia.routerservice.dto.request.RouteRequest;
import ru.disarra.salesavia.routerservice.service.RouteService;

import java.util.Collection;

@RestController
@RequestMapping("/route")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public Collection<RouteDTO> getRoutes(@RequestBody @Validated RouteRequest routeRequest) {
        return routeService.getRoutes(
                routeRequest.getCityA(),
                routeRequest.getCityB(),
                routeRequest.getTransfers(),
                routeRequest.getHoursInTravel(),
                routeRequest.getTravelTime()
        );
    }

}
