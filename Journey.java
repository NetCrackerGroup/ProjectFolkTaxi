package com.netcracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

public class Journey {

    @Id
    @NotNull
    @Column(name = "Route_ID")
    private Long route_Id;


    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Route_ID")
    private Route route;


    @NotNull
    @Column(name = "Date_Of_Journey")
    private LocalDate date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Passenger_in_Journey",
            joinColumns = {@JoinColumn(name = "Journey_ID")},
            inverseJoinColumns = {@JoinColumn(name = "User_ID")}
    )
    Collection<User> users;

    public Long getRoute_Id() {
        return route_Id;
    }

    public void setRoute_Id(Long route_Id) {
        this.route_Id = route_Id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return route_Id.equals(journey.route_Id) &&
                route.equals(journey.route) &&
                date.equals(journey.date) &&
                users.equals(journey.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(route_Id);
    }
}
