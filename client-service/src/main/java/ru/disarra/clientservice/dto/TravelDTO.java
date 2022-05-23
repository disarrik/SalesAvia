package ru.disarra.clientservice.dto;

import java.util.Objects;

public class TravelDTO {
    private final String price;
    private final String arrival;
    private final String departure;

    public TravelDTO(String price, String arrival, String departure) {
        this.price = price;
        this.arrival = arrival;
        this.departure = departure;
    }

    public String getPrice() {
        return price;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelDTO travelDTO = (TravelDTO) o;
        return Objects.equals(price, travelDTO.price) && Objects.equals(arrival, travelDTO.arrival) && Objects.equals(departure, travelDTO.departure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, arrival, departure);
    }
}
