package ru.disarra.salesavia.routerservice.dto;

import ru.disarra.salesavia.routerservice.node.Airport;

public class AirportDTO {
    private String city;
    private String name;

    public AirportDTO(String city, String name) {
        this.city = city;
        this.name = name;
    }

    public static AirportDTO of(Airport airport) {
        return new AirportDTO(
                airport.getCity(),
                airport.getName()
        );
    }

    public AirportDTO() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AirportDTO{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
