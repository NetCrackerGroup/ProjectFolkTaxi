package com.netcracker.DTO;

import com.netcracker.entities.City;
import com.netcracker.entities.Group;
import com.netcracker.entities.Route;

import java.util.Collection;

public class UserDto {

    private Long userId;
    private String fio;
    private String email;
    private String phoneNumber;

    public UserDto() {
    }

    public UserDto(Long userId, String fio, String email, String phoneNumber) {
        this.userId = userId;
        this.fio = fio;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
