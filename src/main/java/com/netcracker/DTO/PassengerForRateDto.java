package com.netcracker.DTO;

public class PassengerForRateDto {



    private Long passengerId;
    private String passengerName;
    private Double passengerRating;

    public PassengerForRateDto() {
    }

    public PassengerForRateDto(Long passengerId, String passengerName, Double passengerRating) {
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.passengerRating = passengerRating;
    }

    public void setPassengerIdId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setPassengerRating(Double passengerRating) {
        this.passengerRating = passengerRating;
    }

}
