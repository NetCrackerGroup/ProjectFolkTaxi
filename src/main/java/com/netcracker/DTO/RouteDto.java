package com.netcracker.DTO;
import org.locationtech.jts.geom.Point;
import com.netcracker.entities.City;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Arrays;

public class RouteDto {
    private double[] routeBegin;
    private double[] routeEnd;
    private BigDecimal price;
    private Integer countOfPlaces;
    private int[] selectedDays;
    private String time;

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setSelectedDays(int[] selectedDays) { this.selectedDays = selectedDays; }


    public int[] getSelectedDays() { return selectedDays; }

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
