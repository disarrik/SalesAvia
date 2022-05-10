package ru.disarra.clientservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.disarra.clientservice.dto.RouteDTO;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class RouteService {

    private final RestTemplate restTemplate;

    @Autowired
    public RouteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RouteDTO> getRoutes(String cityA,
                                              String cityB,
                                              int transfers,
                                              int hoursInTravel,
                                              ZonedDateTime travelTime) {
        UriComponentsBuilder url = UriComponentsBuilder
                .fromHttpUrl("http://ROUTER-SERVICE/route")
                .queryParam("cityA", cityA)
                .queryParam("cityB", cityB)
                .queryParam("transfers", transfers)
                .queryParam("hoursInTravel", hoursInTravel)
                .queryParam("travelTime", travelTime.format(DateTimeFormatter.ISO_INSTANT));

        return List.of(
                restTemplate.getForObject(
                    url.toUriString(),
                    RouteDTO[].class
                )
        );
    }
}
