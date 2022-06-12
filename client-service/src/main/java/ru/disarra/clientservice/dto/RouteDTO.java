package ru.disarra.clientservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class RouteDTO {
    private final List<AirportDTO> airports;
    private final List<TravelDTO> travels;
    private final int price;

    @JsonCreator
    public RouteDTO(
            @JsonProperty("airports") List<AirportDTO> airports,
            @JsonProperty("travels") List<TravelDTO> travels,
            @JsonProperty("price") int price) {
        this.airports = airports;
        this.travels = travels;
        this.price = price;
    }


    public List<AirportDTO> getAirports() {
        return airports;
    }

    public List<TravelDTO> getTravels() {
        return travels;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteDTO routeDTO = (RouteDTO) o;
        return price == routeDTO.price && Objects.equals(airports, routeDTO.airports) && Objects.equals(travels, routeDTO.travels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airports, travels, price);
    }
}

