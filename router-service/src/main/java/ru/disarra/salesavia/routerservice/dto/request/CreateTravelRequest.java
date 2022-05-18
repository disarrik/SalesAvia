package ru.disarra.salesavia.routerservice.dto.request;

import java.time.ZonedDateTime;

public class CreateTravelRequest {
    private String airportA;
    private String airportB;
    private int price;
    private ZonedDateTime arrival;
    private ZonedDateTime departure;

    public CreateTravelRequest(String airportA, String airportB, int price, ZonedDateTime arrival, ZonedDateTime departure) {
        this.airportA = airportA;
        this.airportB = airportB;
        this.price = price;
        this.arrival = arrival;
        this.departure = departure;
    }

    public CreateTravelRequest() {
    }

    public String getAirportA() {
        return airportA;
    }

    public void setAirportA(String airportA) {
        this.airportA = airportA;
    }

    public String getAirportB() {
        return airportB;
    }

    public void setAirportB(String airportB) {
        this.airportB = airportB;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ZonedDateTime getArrival() {
        return arrival;
    }

    public void setArrival(ZonedDateTime arrival) {
        this.arrival = arrival;
    }

    public ZonedDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(ZonedDateTime departure) {
        this.departure = departure;
    }
}
