package com.netcracker.controllers;

import com.netcracker.CustomException.JourneyNotFound;
import com.netcracker.CustomException.NotFoundById;
import com.netcracker.DTO.JourneyFeedbackDto;
import com.netcracker.DTO.JourneyThanksPassegerDTO;
import com.netcracker.DTO.UserIdAndJourneyIdDTO;
import com.netcracker.DTO.mappers.JourneyThanksPassegerMapper;
import com.netcracker.DTO.responses.StatusResponse;
import com.netcracker.entities.Chat;
import com.netcracker.entities.Journey;
import com.netcracker.entities.Route;
import com.netcracker.repositories.ChatRepository;
import com.netcracker.services.JourneyService;
import com.netcracker.services.PaidJourneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("journeys")
public class JourneyController {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private JourneyService journeyService;

    private JourneyThanksPassegerMapper journeyThanksPassegerMapper;
    private PaidJourneyService paidJourneyService;

    @Autowired
    public JourneyController(
            JourneyThanksPassegerMapper journeyThanksPassegerMapper,
            PaidJourneyService paidJourneyService
    ) {
        this.journeyThanksPassegerMapper = journeyThanksPassegerMapper;
        this.paidJourneyService = paidJourneyService;
    }


    @GetMapping("/{Journey_ID}")
    public JourneyFeedbackDto getJourneyByIdForRate(@PathVariable(value = "Journey_ID") Long journeyId) {
        LOG.info("[ getJourneyById : {}", journeyId);

        JourneyFeedbackDto journey = journeyService.getJourneyByIdForRate(journeyId);

        LOG.info("] return : {}", journey);
        return journey;
    }

    @GetMapping("/findJourney/{chatId}/{messageDate}")
    public Long getJourneyByRouteandDate(@PathVariable(value = "chatId") Long chatId, @PathVariable(value = "messageDate") String messageDate) {
        LOG.info("[ getJourneyByRouteandDate : {}, {}", chatId, messageDate);
        Long journeyId = journeyService.getJourneyByRouteandDate(chatId, LocalDate.parse(messageDate));
        LOG.info("] return : {}", journeyId);
        return journeyId;
    }

    @GetMapping("/passeger/info")
    public JourneyThanksPassegerDTO getInfo(@RequestParam(name = "journeyId") Long id) {
        try {
            Journey journey = journeyService.getJourney(id);
            return journeyThanksPassegerMapper.toDto(journey);
        } catch (JourneyNotFound ex) {
            LOG.error("Not found journey", ex);
            return null;
        }
    }

    @GetMapping("/thank")
    public StatusResponse getInfoForStatusThank(
            @RequestParam("journeyId")  Long journeyId,
            @RequestParam("userId") Long userId
            ) {
        LOG.debug("method getInfoForStatusThank");
        try {
            boolean valid = paidJourneyService.checkStatusPaidJourney(new UserIdAndJourneyIdDTO(journeyId, userId));
            if (valid)
                return new StatusResponse("failure");
            return new StatusResponse("success");
        } catch (JourneyNotFound | NotFoundById journeyNotFound) {
            LOG.error("erorr", journeyNotFound);
            return new StatusResponse("failure");
        }
    }
}