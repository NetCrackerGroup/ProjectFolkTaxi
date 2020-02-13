package com.netcracker.DTO;

import com.netcracker.entities.City;

import java.math.BigDecimal;

public class RouteDto {
    private String routeBegin;
    private String routeEnd;
    private BigDecimal price;

    public void setRouteBegin(String routeBegin) {
        this.routeBegin = routeBegin;
    }

    public void setRouteEnd(String routeEnd) {
        this.routeEnd = routeEnd;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRouteBegin() {
        return routeBegin;
    }

    public String getRouteEnd() {
        return routeEnd;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
