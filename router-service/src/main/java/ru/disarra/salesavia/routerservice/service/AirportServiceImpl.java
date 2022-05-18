package ru.disarra.salesavia.routerservice.service;

import org.springframework.stereotype.Service;
import ru.disarra.salesavia.routerservice.node.Airport;
import ru.disarra.salesavia.routerservice.repository.AirportRepository;

@Service
public class AirportServiceImpl implements AirportService{
    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public void create(String name, String city) {
        airportRepository.save(
                new Airport(name, city)
        );
    }
}
