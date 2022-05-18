package ru.disarra.salesavia.routerservice.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.disarra.salesavia.routerservice.repository.TravelRepository;

import java.time.ZonedDateTime;

@Service
@Primary
public class TravelServiceImpl implements TravelService{
    private final TravelRepository travelRepository;

    public TravelServiceImpl(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    @Override
    public void create(String airportNameA, String airportNameB, ZonedDateTime arrival, ZonedDateTime departure, int price) {
        travelRepository.create(airportNameA, airportNameB, arrival, departure, price);
    }
}
