package ru.disarra.salesavia.routerservice.dto;

import ru.disarra.salesavia.routerservice.dto.request.CreateTravelRequest;

public class TravelDTO {
    private String price;
    private String arrival;
    private String departure;

    public TravelDTO(String price, String arrival, String departure) {
        this.price = price;
        this.arrival = arrival;
        this.departure = departure;
    }

    public TravelDTO() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public static TravelDTO of(CreateTravelRequest createTravelRequest) {
        return new TravelDTO(
                String.valueOf(createTravelRequest.getPrice()),
                createTravelRequest.getArrival().toString(),
                createTravelRequest.getDeparture().toString()
        );
    }

    @Override
    public String toString() {
        return "TravelDTO{" +
                "price='" + price + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departure='" + departure + '\'' +
                '}';
    }
}
