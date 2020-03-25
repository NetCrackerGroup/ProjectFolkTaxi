package com.netcracker.DTO.mappers;

import com.netcracker.DTO.UserDto;
import com.netcracker.entities.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {

    public UserMapper() {
        super(User.class, UserDto.class);
    }}
