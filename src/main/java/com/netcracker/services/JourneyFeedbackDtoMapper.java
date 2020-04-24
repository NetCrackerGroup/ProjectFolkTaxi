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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class JourneyFeedbackDtoMapper {

    @Autowired
    public UserRepository usersRepository;

    public JourneyFeedbackDtoMapper () {

    }

    public JourneyFeedbackDto toJourneyFeedbackDto(Journey journey){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User currUser = usersRepository.findUserByEmail(userDetail.getUsername());
        Boolean partOfJourney = false;
        JourneyFeedbackDto journeyFeedbackDto = new JourneyFeedbackDto();
        journeyFeedbackDto.setJourneyId(journey.getJourneyId());
        journeyFeedbackDto.setRouteId(journey.getRoute().getRouteId());
        if (journey.getRoute().getDriverId().getUserId() != currUser.getUserId())
            journeyFeedbackDto.setDriverId(journey.getRoute().getDriverId().getUserId());
        else
            journeyFeedbackDto.setDriverId(0L);
            partOfJourney = true;
        journeyFeedbackDto.setDriverName(journey.getRoute().getDriverId().getFio());
        journeyFeedbackDto.setDriverRating(journey.getRoute().getDriverId().getDriverRating());
        Collection<User> users = journey.getRoute().getUsers();
        Collection<PassengerForRateDto> passengers = null;
        for (User user : users)
        {
            if(user.getUserId() != currUser.getUserId())
                passengers.add(new PassengerForRateDtoMapper().toPassengerForRateDtoMapper(user));
            else
                partOfJourney = true;
        }
        journeyFeedbackDto.setPassengers(passengers);
        if ( partOfJourney == true)
            return journeyFeedbackDto;
        else
            return null;
    }
}
