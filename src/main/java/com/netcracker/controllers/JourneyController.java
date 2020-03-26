package com.netcracker.controllers;

import com.netcracker.DTO.JourneyFeedbackDto;
import com.netcracker.entities.Journey;
import com.netcracker.repositories.JourneyRepository;
import com.netcracker.services.JourneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("journeys")
public class JourneyController {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private JourneyService journeyService;

    @GetMapping("/{Journey_ID}")
    public JourneyFeedbackDto getJourneyById(@PathVariable(value="Journey_ID") Long journeyId){
        LOG.info("[ getJourneyById : {}", journeyId);

        JourneyFeedbackDto journey = journeyService.getJourneyById(journeyId);

        LOG.info("] return : {}", journey);
        return journey;
    }

}
