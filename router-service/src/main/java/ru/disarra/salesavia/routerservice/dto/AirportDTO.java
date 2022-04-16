package ru.disarra.salesavia.routerservice.dto;

public class AirportDTO {
    private String city;
    private String name;

    public AirportDTO(String city, String name) {
        this.city = city;
        this.name = name;
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
