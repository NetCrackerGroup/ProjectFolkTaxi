package com.netcracker.services;

import com.netcracker.DTO.JourneyFeedbackDto;
import com.netcracker.DTO.PassengerForRateDto;
import com.netcracker.entities.Journey;
import com.netcracker.entities.User;

import java.util.Collection;

public class JourneyFeedbackDtoMapper {


    public JourneyFeedbackDtoMapper () {

    }

    public JourneyFeedbackDto toJourneyFeedbackDto(Journey journey){
        JourneyFeedbackDto journeyFeedbackDto = new JourneyFeedbackDto();
        journeyFeedbackDto.setJourneyId(journey.getJourneyId());
        journeyFeedbackDto.setRouteId(journey.getRoute().getRouteId());
        journeyFeedbackDto.setDriverId(journey.getRoute().getDriverId().getUserId());
        journeyFeedbackDto.setDriverName(journey.getRoute().getDriverId().getFio());
        journeyFeedbackDto.setDriverRating(journey.getRoute().getDriverId().getDriverRating());
        Collection<User> users = journey.getRoute().getUsers();
        Collection<PassengerForRateDto> passengers = null;
        for (User user : users)
        {
            passengers.add(new PassengerForRateDtoMapper().toPassengerForRateDtoMapper(user));
        }
        journeyFeedbackDto.setPassengers(passengers);
        return journeyFeedbackDto;
    }
}
