package com.netcracker.controllers;

import com.netcracker.DTO.JourneyFeedbackDto;
import com.netcracker.entities.Chat;
import com.netcracker.entities.Route;
import com.netcracker.repositories.ChatRepository;
import com.netcracker.services.JourneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("journeys")
public class JourneyController {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private JourneyService journeyService;



    @GetMapping("/{Journey_ID}")
    public JourneyFeedbackDto getJourneyByIdForRate(@PathVariable(value="Journey_ID") Long journeyId){
        LOG.info("[ getJourneyById : {}", journeyId);

        JourneyFeedbackDto journey = journeyService.getJourneyByIdForRate(journeyId);

        LOG.info("] return : {}", journey);
        return journey;
    }

    @GetMapping("/findJourney/{chatId}/{messageDate}")
    public Long getJourneyByRouteandDate(@PathVariable(value="chatId") Long chatId, @PathVariable(value="messageDate") String messageDate) {
        LOG.info("[ getJourneyByRouteandDate : {}, {}", chatId, messageDate);
        Long journeyId =  journeyService.getJourneyByRouteandDate(chatId, LocalDate.parse(messageDate));
        LOG.info("] return : {}", journeyId);
        return journeyId;
    }

}
