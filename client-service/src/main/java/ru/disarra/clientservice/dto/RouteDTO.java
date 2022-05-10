package ru.disarra.clientservice.dto;

import java.util.List;

public class RouteDTO {
    private List<AirportDTO> airports;
    private List<TravelDTO> travels;
    private int price;

    public RouteDTO(List<AirportDTO> airports, List<TravelDTO> travels, int price) {
        this.airports = airports;
        this.travels = travels;
        this.price = price;
    }

    public RouteDTO() {
    }

    public List<AirportDTO> getAirports() {
        return airports;
    }

    public void setAirports(List<AirportDTO> airports) {
        this.airports = airports;
    }

    public List<TravelDTO> getTravels() {
        return travels;
    }

    public void setTravels(List<TravelDTO> travels) {
        this.travels = travels;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RouteDTO{" +
                "airports=" + airports +
                ", travels=" + travels +
                '}';
    }
}

