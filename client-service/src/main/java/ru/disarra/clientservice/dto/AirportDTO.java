package ru.disarra.clientservice.dto;

import java.util.Objects;

public class AirportDTO {
    private final String city;
    private final String name;

    public AirportDTO(String city, String name) {
        this.city = city;
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirportDTO that = (AirportDTO) o;
        return Objects.equals(city, that.city) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, name);
    }
}
