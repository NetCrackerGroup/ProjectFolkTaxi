package com.netcracker.services;

import com.netcracker.DTO.UserDto;
import com.netcracker.DTO.UserRouteDto;
import com.netcracker.entities.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserRouteMapper extends AbstractMapper<User, UserRouteDto> {
    public UserRouteMapper() {
        super(User.class, UserRouteDto.class);
    }

}
