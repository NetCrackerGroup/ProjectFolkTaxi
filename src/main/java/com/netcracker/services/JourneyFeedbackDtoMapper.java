package com.netcracker.services;

import com.netcracker.DTO.JourneyFeedbackDto;
import com.netcracker.entities.Journey;

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
        return journeyFeedbackDto;
    }
}
