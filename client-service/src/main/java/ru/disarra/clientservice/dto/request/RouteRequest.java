package ru.disarra.clientservice.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RouteRequest {
    @NotNull
    @NotBlank
    private String cityA;
    @NotNull
    @NotBlank
    private String cityB;
    @Min(0)
    private int transfers = 0;
    @Min(1)
    private int hoursInTravel = 24;
    @NotNull
    private LocalDateTime travelTime;

    public RouteRequest() {
    }

    public RouteRequest(String cityA, String cityB, int transfers, int hoursInTravel, LocalDateTime zonedDateTime) {
        this.cityA = cityA;
        this.cityB = cityB;
        this.transfers = transfers;
        this.hoursInTravel = hoursInTravel;
        this.travelTime = zonedDateTime;
    }

    public String getCityA() {
        return cityA;
    }

    public void setCityA(String cityA) {
        this.cityA = cityA;
    }

    public String getCityB() {
        return cityB;
    }

    public void setCityB(String cityB) {
        this.cityB = cityB;
    }

    public int getTransfers() {
        return transfers;
    }

    public void setTransfers(int transfers) {
        this.transfers = transfers;
    }

    public int getHoursInTravel() {
        return hoursInTravel;
    }

    public void setHoursInTravel(int hoursInTravel) {
        this.hoursInTravel = hoursInTravel;
    }

    public LocalDateTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalDateTime travelTime) {
        this.travelTime = travelTime;
    }
}
