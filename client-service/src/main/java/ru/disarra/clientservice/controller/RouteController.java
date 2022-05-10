package ru.disarra.clientservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.disarra.clientservice.dto.RouteDTO;
import ru.disarra.clientservice.dto.request.RouteRequest;
import ru.disarra.clientservice.service.RouteService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping("/search")
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping(params = {})
    public String index() {
        return "search";
    }

    @GetMapping(params = {"cityA" , "cityB", "transfers", "hoursInTravel", "travelTime"})
    public String index(RouteRequest routeRequest, TimeZone timezone, Model model) {
        List<RouteDTO> routes = routeService.getRoutes(
                routeRequest.getCityA(),
                routeRequest.getCityB(),
                routeRequest.getTransfers(),
                routeRequest.getHoursInTravel(),
                ZonedDateTime.of(routeRequest.getTravelTime(), timezone.toZoneId())
        );
        model.addAttribute("routes", routes);
        return "search";
    }
}
