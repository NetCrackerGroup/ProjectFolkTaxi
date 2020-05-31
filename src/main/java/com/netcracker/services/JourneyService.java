package com.netcracker.services;

import com.netcracker.CustomException.JourneyNotFound;
import com.netcracker.DTO.JourneyFeedbackDto;
import com.netcracker.entities.*;
import com.netcracker.repositories.ChatRepository;
import com.netcracker.repositories.JourneyRepository;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class JourneyService {

    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public JourneyFeedbackDtoMapper journeyFeedbackDtoMapper;

    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private ChatRepository chatRepository;

    public JourneyFeedbackDto getJourneyByIdForRate(Long journeyId){
        LOG.info("[ getJourneyById : {}", journeyId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User currUser = userRepository.findUserByEmail(userDetail.getUsername());


        Optional<Journey> journey = journeyRepository.findById(journeyId);

        LOG.info("] return : {}", journey.get());
        return journey.isPresent() ? journeyFeedbackDtoMapper.toJourneyFeedbackDto(journey.get(), currUser) : null;
    }


    public Collection<User> getPassengersInJourney (Long journeyId){

        return journeyRepository.findById(journeyId).get().getUsers();
    }
  
    public Long getJourneyByRouteandDate(Long chatId, LocalDate messageDate) {
        Chat chat = chatRepository.findByChatId(chatId);

        Route route = chat.getRoute();

        Journey journey =  journeyRepository.getJourneyByRouteAndDate(route, messageDate);

        return journey.getJourneyId();
    }

    public Journey getJourney(Long id) throws JourneyNotFound {
        Optional<Journey> optionalJourney = journeyRepository.findById(id);
        if (!optionalJourney.isPresent()) {
            throw new JourneyNotFound();
        }
        LOG.debug("Journey : {}", id);
        return optionalJourney.get();
    }
}
