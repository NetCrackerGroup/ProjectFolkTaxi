package com.netcracker.DTO;

public class InfoToThankPassengerDTO {

    private Long userID;
    private Long journeyID;
    private double price;
    private String code;


    public InfoToThankPassengerDTO() {
    }

    public InfoToThankPassengerDTO(Long userID, Long journeyID, double price, String code) {
        this.userID = userID;
        this.journeyID = journeyID;
        this.price = price;
        this.code = code;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getJourneyID() {
        return journeyID;
    }

    public void setJourneyID(Long journeyID) {
        this.journeyID = journeyID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "InfoToThankPassengerDTO{" +
                "userID=" + userID +
                ", journeyID=" + journeyID +
                ", price=" + price +
                ", code='" + code + '\'' +
                '}';
    }
}
