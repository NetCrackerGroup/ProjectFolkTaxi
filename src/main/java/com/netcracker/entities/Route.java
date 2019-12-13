package com.netcracker.entities;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Route")
public class Route {
    @Id
    @NotNull
    @Column(name = "Route_ID")
    private Long route_Id;

    @NotNull
    @Size(min=1,max=200)
    @Column(name = "Route_Beging")
    private String route_beging;

    @NotNull
    @Size(min=1,max=200)
    @Column(name = "Route_End")
    private String route_end;


    @Column(name = "Price")
    private Long price;


    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "User_ID")
    private User driver;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "City_ID")
    private City city;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Passenger_In_Route",
            joinColumns = {@JoinColumn(name = "Route_ID")},
            inverseJoinColumns = {@JoinColumn(name = "User_ID")}
    )
    private Collection<User> users_route;

    public Long getRoute_Id() {
        return route_Id;
    }

    public void setRoute_Id(Long route_Id) {
        this.route_Id = route_Id;
    }

    public String getRoute_beging() {
        return route_beging;
    }

    public void setRoute_beging(String route_beging) {
        this.route_beging = route_beging;
    }

    public String getRoute_end() {
        return route_end;
    }

    public void setRoute_end(String route_end) {
        this.route_end = route_end;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Collection<User> getUsers_route() {
        return users_route;
    }

    public void setUsers_route(Collection<User> users_route) {
        this.users_route = users_route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return route_Id.equals(route.route_Id) &&
                route_beging.equals(route.route_beging) &&
                route_end.equals(route.route_end) &&
                Objects.equals(price, route.price) &&
                driver.equals(route.driver) &&
                city.equals(route.city) &&
                Objects.equals(users_route, route.users_route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(route_Id);
    }
}

