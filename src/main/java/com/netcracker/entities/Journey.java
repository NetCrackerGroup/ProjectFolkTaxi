package com.netcracker.entities;

import org.hibernate.validator.constraints.CodePointLength;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Journeys")
public class Journey {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "journey_id_generator")
    @SequenceGenerator(name = "journey_id_generator", sequenceName = "journey_id_seq", allocationSize = 1)
    @Column(name = "Journey_ID")
    private Long journeyId;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Route_ID")
    private Route route;

    public Journey() { }
    public Journey(LocalDate date,Collection<User> users, Route route, User driver,
                   Boolean isStarted, Boolean isfinished) {

        this.date = date;
        this.users = users;
        this.route = route;
//        this.driverId = driver;
        this.isStarted = isStarted;
        this.isfinished = isfinished;
    }

    @NotNull
    @Column(name = "Date_Of_Journey")
    private LocalDate date;

    @NotNull
    @Column(name = "is_started")
    private Boolean isStarted;

    @NotNull
    @Column(name = "is_finished")
    private Boolean isfinished;

    public Boolean getStarted() {
        return isStarted;
    }

    public void setStarted(Boolean started) {
        isStarted = started;
    }

    public Boolean getIsfinished() {
        return isfinished;
    }

    public void setIsfinished(Boolean isfinished) {
        this.isfinished = isfinished;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Passenger_in_Journey",
            joinColumns = {@JoinColumn(name = "Journey_ID")},
            inverseJoinColumns = {@JoinColumn(name = "User_ID")}
    )
    private Collection<User> users;

//    public User getDriverId() {
//        return driverId;
//    }
//
//    public void setDriverId(User driverId) {
//        this.driverId = driverId;
//    }

//    @NotNull
//    @JoinColumn(name = "Driver_ID")
//    @OneToOne()
//    private User driverId;

    public Long getRoute_Id() {
        return journeyId;
    }

    public void setRoute_Id(Long route_Id) {
        this.journeyId = route_Id;
    }
    public Long getJourneyId() { return journeyId; }

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

    public Collection<User> getUsers() { return users; }

    public void setUsers(Collection<User> users) {
        for (User u: users) {
            u.setOneMoreJourney(this);
        }

    }

    /*public User getDriverId() {
        return driverId;
    }*/

    /*public void setDriverId(User driverId) {
        this.driverId = driverId;
    }*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return Objects.equals(journeyId, journey.journeyId) &&
                Objects.equals(route, journey.route) &&
                Objects.equals(date, journey.date) &&
                Objects.equals(isStarted, journey.isStarted) &&
                Objects.equals(isfinished, journey.isfinished) &&
                Objects.equals(users, journey.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(journeyId, route, date, isStarted, isfinished, users);
    }

    public void setUser(User user) {
            this.users.add(user);
    }
}
