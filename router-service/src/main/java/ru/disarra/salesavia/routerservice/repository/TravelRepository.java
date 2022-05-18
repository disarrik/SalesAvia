package ru.disarra.salesavia.routerservice.repository;

import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

public interface TravelRepository {
    void create(String airportNameA, String airportNameB, ZonedDateTime arrival, ZonedDateTime departure, int price);
}
