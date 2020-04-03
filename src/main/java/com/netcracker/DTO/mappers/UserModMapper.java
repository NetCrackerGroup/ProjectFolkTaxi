package com.netcracker.DTO.mappers;

import com.netcracker.DTO.UserDto;
import com.netcracker.DTO.UserModerDto;
import com.netcracker.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserModMapper extends AbstractMapper<User, UserModerDto> {
    public UserModMapper() {
        super(User.class, UserModerDto.class);
    }
}
