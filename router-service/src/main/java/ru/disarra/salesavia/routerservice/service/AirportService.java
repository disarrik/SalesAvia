package ru.disarra.salesavia.routerservice.service;

import java.util.Set;

public interface AirportService {
    void create(String name, String city);

    Set<String> getAirportsCities();

}
