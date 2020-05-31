package com.netcracker.services;

import com.netcracker.CustomException.JourneyNotFound;
import com.netcracker.CustomException.NotFoundById;
import com.netcracker.DTO.UserIdAndJourneyIdDTO;
import com.netcracker.DTO.UserSecDto;
import com.netcracker.entities.*;
import com.netcracker.repositories.PaidJourneyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaidJourneyService {

    private final Logger LOG = LoggerFactory.getLogger(PaidJourneyService.class);

    private PaidJourneyRepository paidJourneyRepository;
    private StatusThankService statusThankService;
    private UsersService usersService;
    private JourneyService journeyService;

    @Autowired
    public PaidJourneyService(
            PaidJourneyRepository paidJourneyRepository,
            StatusThankService statusThankService,
            UsersService usersService,
            JourneyService journeyService
    ) {
        this.paidJourneyRepository = paidJourneyRepository;
        this.statusThankService = statusThankService;
        this.journeyService = journeyService;
        this.usersService = usersService;
    }

    /**
     * СОздает Оплаченную поездку
     * @param user
     * @param journey
     */
    public void addingUserInPaidJourneys(User user, Journey journey) throws NotFoundById {
        StatusThank statusThank = this.statusThankService.getStatusThankById(1L);
        PaidJourney paidJourney = new PaidJourney(user, journey, statusThank);
        paidJourneyRepository.save(paidJourney);
    }

    /***
     * Вернет true, если пользователь, поблагодарил каждого водителя,
     * либо не участвовал ни в одном.
     * @param user
     * @return
     */
    public boolean userCheckForUnpaidJourneys(User user) {
        return getListNotPaidJorney(user).size() == 0;
    }

    /**
     * Вернет колекцию Не оплаченных(благодарность водителю) поездок,
     * в которых пользователь участвовал.
     * @param user
     * @return
     */
    public Collection<PaidJourney> getListNotPaidJorney(User user) {
        return paidJourneyRepository.findAllByUser(user)
                .stream()
                .filter(x-> !x.getStatus().getName().equals("expectation"))
                .collect(Collectors.toCollection(LinkedList::new))
        ;
    }

    public PaidJourney getPaidJourney(User user, Journey journey) throws NotFoundById {
        LOG.debug("getPaidJourney");
        Optional<PaidJourney> optionalPaidJourney =  paidJourneyRepository.findPaidJourneyByUserAndJourney(user, journey);
        if ( !optionalPaidJourney.isPresent()) {
            throw new NotFoundById();
        }
        return optionalPaidJourney.get();
    }

    public void changeStatusPaidJourney(PaidJourney paidJourney) throws NotFoundById {
        StatusThank statusThank = statusThankService.getStatusThankById(2L);
        paidJourney.setStatus(statusThank);
        paidJourneyRepository.save(paidJourney);
    }

    public boolean checkStatusPaidJourney(UserIdAndJourneyIdDTO userIdAndJourneyIdDTO) throws JourneyNotFound, NotFoundById {
        User user = usersService.getUser(userIdAndJourneyIdDTO.getUserId());
        Journey journey = journeyService.getJourney(userIdAndJourneyIdDTO.getJourneyId());
        return validStatusPaidJourney(user, journey);
    }

    public boolean validStatusPaidJourney(User user, Journey journey) throws NotFoundById {
        Optional<PaidJourney> opPaidJourney = paidJourneyRepository.findPaidJourneyByUserAndJourney(user, journey);
        if (!opPaidJourney.isPresent())
            throw new NotFoundById();
        StatusThank statusThank = opPaidJourney.get().getStatus();
        if ( statusThank.equals(statusThankService.getStatusThankById(1L)))
            return true;
        return false;
    }
}