package ru.disarra.salesavia.routerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.disarra.salesavia.routerservice.service.AirportService;

import java.util.Set;

@RestController
@RequestMapping("/city")
public class CityController {

    private final AirportService airportService;


    public CityController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public Set<String> getAllCities() {
        return airportService.getAirportsCities();
    }
}
