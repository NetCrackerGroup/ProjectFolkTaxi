package com.netcracker.DTO;

import com.netcracker.entities.City;
import com.netcracker.entities.User;

public class UserSecDto {

    private String email;
    private String password;
    private String phoneNumber;
    private int cityId;
    private String name;


    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) { this.cityId = cityId; }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser(City city){
        User user = new User(name, email, phoneNumber, city, password, "ROLE_USER");
        return user;
    }

//    public static UserDto fromUser(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setFIO(user.getFio());
//        userDto.setEmail(user.getEmail());
//
//        return userDto;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
