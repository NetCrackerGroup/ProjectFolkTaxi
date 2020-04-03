package com.netcracker.services;

import com.netcracker.DTO.JourneyFeedbackDto;
import com.netcracker.entities.Journey;
import com.netcracker.entities.User;
import com.netcracker.repositories.JourneyRepository;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JourneyService {

    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private JourneyRepository journeyRepository;

    public JourneyFeedbackDto getJourneyById(Long journeyId){
        LOG.info("[ getJourneyById : {}", journeyId);

        Optional<Journey> journey = journeyRepository.findById(journeyId);

        LOG.info("] return : {}", journey.get());
        return journey.isPresent() ? (new JourneyFeedbackDtoMapper()).toJourneyFeedbackDto(journey.get()) : null;
    }
}