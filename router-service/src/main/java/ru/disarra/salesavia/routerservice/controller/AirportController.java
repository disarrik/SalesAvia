package ru.disarra.salesavia.routerservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.disarra.salesavia.routerservice.dto.AirportDTO;
import ru.disarra.salesavia.routerservice.service.AirportService;

import java.util.Set;

@RestController
@RequestMapping("/airport")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }


    @PostMapping
    public AirportDTO create(@RequestBody AirportDTO airportDTO) {
        airportService.create(
                airportDTO.getName(),
                airportDTO.getCity()
        );
        return airportDTO;
    }
}
