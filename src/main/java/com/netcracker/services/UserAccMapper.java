package com.netcracker.services;

import com.netcracker.DTO.UserAccDto;
import com.netcracker.entities.User;

import java.util.Objects;
//import org.springframework.stereotype.Component;

//@Component
public class UserAccMapper {//extends AbstractMapper<User, UserAccDto> {


    public UserAccMapper () {

    }

    public UserAccDto toUserAccDto(User user){
        UserAccDto userAccDto = new UserAccDto();
        userAccDto.setFio(user.getFio());
        userAccDto.setPhoneNumber(user.getPhoneNumber());
        userAccDto.setCityName(user.getCityId().getCityName());
        userAccDto.setPassengerRating(Math.round(user.getPassengerRating() * 100.0) / 100.0);
        userAccDto.setDriverRating(Math.round(user.getDriverRating() * 100.0) / 100.0);
        userAccDto.setInfo(user.getInfo());
        userAccDto.setImage(user.getImage());
        return userAccDto;
    }

    /*public UserAccMapper() {
        super(User.class, UserAccDto.class);
    }


    /*@PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(User.class, UserAccDto.class).addMapping(User :: getCityId, UserAccDto :: setCityName).setPostConverter(
                context -> {
                    LOG.debug("User");
                    User user = context.getSource();
                    UserAccDto userAccDto = context.getDestination();
                    String cityName = user.getCityId().getCityName();
                    userAccDto.setCityName(cityName);
                    return userAccDto;
                }
        );
        mapper.createTypeMap(City.class, String.class).setPostConverter(context -> {
            LOG.debug("String");
            String name_city = context.getDestination();
            City city = context.getSource();
            name_city = city.getCityName();
            return context.getDestination();
        });
    }*/

}
