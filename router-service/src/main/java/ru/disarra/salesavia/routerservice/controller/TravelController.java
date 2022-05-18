package ru.disarra.salesavia.routerservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.disarra.salesavia.routerservice.dto.TravelDTO;
import ru.disarra.salesavia.routerservice.dto.request.CreateTravelRequest;
import ru.disarra.salesavia.routerservice.service.TravelService;

@RestController
@RequestMapping("/travel")
public class TravelController {
    private final TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    @PostMapping
    public TravelDTO create(@RequestBody CreateTravelRequest createTravelRequest) {
        travelService.create(
                createTravelRequest.getAirportA(),
                createTravelRequest.getAirportB(),
                createTravelRequest.getArrival(),
                createTravelRequest.getDeparture(),
                createTravelRequest.getPrice()
        );
        return TravelDTO.of(createTravelRequest);
    }
}
