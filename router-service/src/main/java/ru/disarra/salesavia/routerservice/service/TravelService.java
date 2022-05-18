package ru.disarra.salesavia.routerservice.service;

import java.time.ZonedDateTime;

public interface TravelService {
    void create(String airportNameA, String airportNameB, ZonedDateTime arrival, ZonedDateTime departure, int price);

}
