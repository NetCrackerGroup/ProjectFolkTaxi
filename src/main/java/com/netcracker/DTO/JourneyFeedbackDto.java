package com.netcracker.DTO;

import com.netcracker.entities.Route;

public class JourneyFeedbackDto {

    private Long journeyId;
    private Long routeId;
    private Long driverId;
    private String driverName;
    private Double driverRating;

    public JourneyFeedbackDto(){}

    public JourneyFeedbackDto(Long journeyId, Long routeId, Long driverId, String driverName, Double driverRating) {
        this.journeyId = journeyId;
        this.routeId = routeId;
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverRating = driverRating;
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

}
