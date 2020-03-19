package com.netcracker.DTO;

import com.netcracker.entities.DriverRating;

import com.netcracker.entities.City;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class RouteDto {
    private double[] routeBegin;
    private double[] routeEnd;
    private BigDecimal price;
    private Date startDate;
    private Integer countOfPlaces;
    private UserRouteDto userDto;

    public void setUserRouteDto(UserRouteDto userDto) { this.userDto = userDto; }

    public UserRouteDto getUserRouteDto() { return userDto; }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setRouteBegin(double[] routeBegin) { this.routeBegin = routeBegin; }

    public void setRouteEnd(double[] routeEnd) {
        this.routeEnd = routeEnd;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double[] getRouteBegin() {
        return routeBegin;
    }

    public void setcountOfPlaces(Integer countOfPlaces) { this.countOfPlaces = countOfPlaces; }

    public Integer getcountOfPlaces() { return countOfPlaces; }

    public double[] getRouteEnd() {
        return routeEnd;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "RouteDto{" +
                "routeBegin=" + Arrays.toString(routeBegin) +
                ", routeEnd=" + Arrays.toString(routeEnd) +
                ", price=" + price +
                ", countOfPlaces=" + countOfPlaces +
                '}';
    }
}
