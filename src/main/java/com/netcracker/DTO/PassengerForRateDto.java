package com.netcracker.DTO;

public class PassengerForRateDto {



    private Long passengerId;
    private String passengerName;

    public PassengerForRateDto() {
    }

    public PassengerForRateDto(Long passengerId, String passengerName, Double passengerRating) {
        this.passengerId = passengerId;
        this.passengerName = passengerName;
    }

    public void setPassengerIdId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

}
