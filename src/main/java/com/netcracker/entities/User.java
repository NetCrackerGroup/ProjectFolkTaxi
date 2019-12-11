package com.netcracker.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "User_1")
public class User {

    @Id
    @NotNull
    @Column(name = "User_ID")
    private Long userId;

    @NotNull
    @Column(name = "City_ID")
    private Long cityId;

    @NotNull
    @Column(name = "FIO")
    private String fio;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @ManyToMany(mappedBy ="User_1",fetch=FetchType.EAGER)
    Collection<Route> routes;

    public Collection<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Collection<Route> routes) {
        this.routes = routes;
    }

    public Collection<Journey> getJournies() {
        return journies;
    }

    public void setJournies(Collection<Journey> journies) {
        this.journies = journies;
    }

    @ManyToMany(mappedBy ="User_1",fetch=FetchType.EAGER)
    Collection<Journey> journies;



    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
