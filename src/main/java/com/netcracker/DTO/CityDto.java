package com.netcracker.DTO;

import com.netcracker.entities.City;
import com.netcracker.entities.User;

public class CityDto {

    private String cityName;

    public CityDto (){}

    public CityDto(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
