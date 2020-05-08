package com.netcracker.services;

import com.netcracker.DTO.PassengerForRateDto;
import com.netcracker.entities.User;

public class PassengerForRateDtoMapper {

    public PassengerForRateDtoMapper () {

    }

    public PassengerForRateDto toPassengerForRateDtoMapper(User user){
        PassengerForRateDto passengerForRateDto = new PassengerForRateDto();
        passengerForRateDto.setPassengerIdId(user.getUserId());
        passengerForRateDto.setPassengerName(user.getFio());
        return passengerForRateDto;
    }
}
