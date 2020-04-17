package com.netcracker.DTO;


import java.util.Collection;

public class JourneyFeedbackDto {

    private Long journeyId;
    private Long routeId;
    private Long driverId;
    private String driverName;
    private Double driverRating;
    private Collection<PassengerForRateDto> passengers;

    public JourneyFeedbackDto(){}

    public JourneyFeedbackDto(Long journeyId, Long routeId, Long driverId, String driverName, Double driverRating, Collection<PassengerForRateDto> passengers) {
        this.journeyId = journeyId;
        this.routeId = routeId;
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverRating = driverRating;
        this.passengers = passengers;
    }
    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Double getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(Double driverRating) {
        this.driverRating = driverRating;
    }

    public void setPassengers(Collection<PassengerForRateDto> passengers) {
        this.passengers = passengers;
    }


}
