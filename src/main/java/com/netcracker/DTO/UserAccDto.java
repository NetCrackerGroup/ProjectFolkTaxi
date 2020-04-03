package com.netcracker.DTO;

import com.netcracker.entities.City;
import com.netcracker.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAccDto {

    //private static final Logger LOG = LoggerFactory.getLogger(UserAccDto.class);

    private String fio;
    private String phoneNumber;
    private String cityName;
    private Double passengerRating;
    private Double driverRating;
    private String info;
    private String image;

    public UserAccDto() {
    }

    public UserAccDto( String fio,
                       String phoneNumber,
                       String cityName,
                       Double passengerRating,
                       Double driverRating,
                       String info,
                       String image) {
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.cityName = cityName;
        this.passengerRating =  passengerRating;
        this.driverRating = driverRating;
        this.info = info;
        this.image = image;
    }


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(Double driverRating) {
        this.driverRating = driverRating;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        //LOG.debug("setCityName : {}", cityName);
        this.cityName = cityName;
        //LOG.debug("UserAccDto : {}", this);
    }

    public Double getPassengerRating() {
        return passengerRating;
    }

    public void setPassengerRating(Double passengerRating) { this.passengerRating = passengerRating; }

    public String getInfo() { return info; }

    public void setInfo(String info) { this.info = info; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }
}
