package com.netcracker.entities;


import org.hibernate.validator.constraints.Range;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_id_generator")
    @SequenceGenerator(name = "route_id_generator", sequenceName = "route_id_seq", allocationSize = 1)
    @Column(name = "route_id")
    private Long routeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    //убрал @Range(min=0, max=90) и заработало, почему?
    @Column(name = "route_begin")
    private Point routeBegin;

    //убрал @Range(min=0, max=90) и заработало, почему?
    @Column(name = "route_end")
    private Point routeEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
    
    public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Column(name = "price")
    private BigDecimal price;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    private User driverId;

    @Column(name = "journeys")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Journey> journeys;

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<User> getUsers() {
        return users;
    }

    @Column(name = "count_of_places")
    private Integer countOfPlaces;


    @ManyToMany(fetch = FetchType.LAZY )

    @JoinTable(
            name = "passenger_in_route",
            joinColumns = { @JoinColumn(name = "route_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    Collection<User> users;

    @Column(name = "start_day")
    private Date startDate;

    public Route() {
    }

    public void setCountOfPlaces(Integer countOfPlaces) {
        this.countOfPlaces = countOfPlaces;
    }

    public Integer getCountOfPlaces() {
        return countOfPlaces;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    //убрал @Range(min=0, max=90) и заработало, почему?
    public Route(  Point routeBegin,
                   Point routeEnd,
                   BigDecimal price,
                   Date startDate,
                   Integer countOfPlaces) {
        this.routeBegin = routeBegin;
        this.routeEnd = routeEnd;
        this.price = price;
        this.countOfPlaces = countOfPlaces;
        this.startDate = startDate;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Point getRouteBegin() {
        return routeBegin;
    }

    public void setRouteBegin(Point routeBegin) {
        this.routeBegin = routeBegin;
    }

    public Point getRouteEnd() {
        return routeEnd;
    }

    public void setRouteEnd(Point routeEnd) {
        this.routeEnd = routeEnd;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getDriverId() {
        return driverId;
    }

    public void setDriverId(User driverId) {
        this.driverId = driverId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Route route = (Route) obj;
        return routeId.equals(route.routeId);
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", city='" + city  +
                ", routeBegin='" + routeBegin +
                ", routeEnd=" + routeEnd +
                ", price=" + price +
                ", driver=" + driverId +
                '}';
    }
}