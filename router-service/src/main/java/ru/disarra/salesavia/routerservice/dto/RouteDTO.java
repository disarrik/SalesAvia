package ru.disarra.salesavia.routerservice.dto;

import java.util.List;

public class RouteDTO {
    private List<AirportDTO> airports;
    private List<TravelDTO> travels;

    public RouteDTO(List<AirportDTO> airports, List<TravelDTO> travels) {
        this.airports = airports;
        this.travels = travels;
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

    @Override
    public String toString() {
        return "RouteDTO{" +
                "airports=" + airports +
                ", travels=" + travels +
                '}';
    }
}
