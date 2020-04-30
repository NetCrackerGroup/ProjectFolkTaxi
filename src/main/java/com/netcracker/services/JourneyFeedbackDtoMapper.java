package com.netcracker.services;

import com.netcracker.DTO.JourneyFeedbackDto;
import com.netcracker.DTO.PassengerForRateDto;
import com.netcracker.entities.Journey;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;


public class JourneyFeedbackDtoMapper {


    public JourneyFeedbackDtoMapper() {

    }

    public JourneyFeedbackDto toJourneyFeedbackDto(Journey journey, User currUser) {

        Boolean partOfJourney = false;

        JourneyFeedbackDto journeyFeedbackDto = new JourneyFeedbackDto();
        journeyFeedbackDto.setJourneyId(journey.getJourneyId());
        journeyFeedbackDto.setRouteId(journey.getRoute().getRouteId());
        if (journey.getRoute().getDriverId().getUserId() != currUser.getUserId())
            journeyFeedbackDto.setDriverId(journey.getRoute().getDriverId().getUserId());
        else {
            journeyFeedbackDto.setDriverId(0L);
            partOfJourney = true;
        }
        journeyFeedbackDto.setDriverName(journey.getRoute().getDriverId().getFio());
        journeyFeedbackDto.setDriverRating(journey.getRoute().getDriverId().getDriverRating() == null ? null: Math.round(journey.getRoute().getDriverId().getDriverRating() * 100.0) / 100.0);

        Collection<User> users = journey.getRoute().getUsers();
        Collection<PassengerForRateDto> passengers = null;
        for (User user : users) {
            if (user.getUserId() != currUser.getUserId())
                passengers.add(new PassengerForRateDtoMapper().toPassengerForRateDtoMapper(user));
            else
                partOfJourney = true;
        }
        journeyFeedbackDto.setPassengers(passengers);

        return partOfJourney ? journeyFeedbackDto : null;

    }
}
