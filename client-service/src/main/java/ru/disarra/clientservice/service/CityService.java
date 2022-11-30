package ru.disarra.clientservice.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CityService {

    private Set<String> allAvailableCities = Set.of();
    private final RestTemplate restTemplate;

    public CityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public synchronized List<String> getCitiesWithPrefix(String prefix) {
        return allAvailableCities.stream()
                .filter(c -> c.startsWith(prefix))
                .sorted()
                .collect(Collectors.toList());
    }

    @Scheduled(fixedDelay = 60000)
    private synchronized void fetchCities() {
        UriComponentsBuilder url = UriComponentsBuilder
                .fromHttpUrl("http://ROUTER-SERVICE/city");
        allAvailableCities = restTemplate.getForObject(url.toUriString(), Set.class);
        System.out.println("Fetched " + allAvailableCities.size() + " cities");
    }
}
