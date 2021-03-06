package com.netcracker.DTO;

import com.netcracker.entities.DriverRating;
import org.locationtech.jts.geom.Point;
import com.netcracker.entities.City;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class RouteDto {
    private double[] routeBegin;
    private double[] routeEnd;
    private BigDecimal price;
    private String driverName;
    private Date startDate;
    private Integer routeId;
    private Integer countOfPlaces;
    private UserRouteDto userDto;
    private int distance;
    private int optimality;
    private Long groupId;
    
    public void setUserRouteDto(UserRouteDto userDto) { this.userDto = userDto; }

    public UserRouteDto getUserRouteDto() { return userDto; }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setDriverName(String name) {
    	driverName = name;
    }

    public String getDriverName(String name) {
    	return driverName;
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

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getOptimality() {
		return optimality;
	}

	public void setOptimality(int optimality) {
		this.optimality = optimality;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
}
