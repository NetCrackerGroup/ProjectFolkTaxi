package com.netcracker.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "City_ID")
    private Integer cityId;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "City_Name")
    private String cityName;

    public City(){}
    public City(
            @NotNull @Size(min = 1, max = 50)  String cityMap,
            @NotNull @Size(min = 1, max = 50)  String cityName
    ) {
        this.cityMap = cityMap;
        this.cityName = cityName;
    }

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "City_Map")
    private String cityMap;

    public Integer getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityMap() {
        return cityMap;
    }
}
