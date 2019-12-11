package com.netcracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Objects;

public class City {
    @Id
    @NotNull
    @Column(name = "City_ID")
    private Long city_Id;

    @NotNull
    @Size(min=1,max=200)
    @Column(name = "City_Name")
    private String city_name;


    @NotNull
    @Column(name = "City_Map")
    private String city_map;




    public Long getCity_Id() {
        return city_Id;
    }

    public String getCity_name() {
        return city_name;
    }
    public String getCity_map() {
        return city_map;
    }


    public void setCity_Id(Long city_Id) {
        this.city_Id = city_Id;
    }
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
    public void setCity_map(String city_map) {
        this.city_map = city_map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return city_Id.equals(city.city_Id) &&
                city_name.equals(city.city_name) &&
                city_map.equals(city.city_map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city_Id);
    }
}
