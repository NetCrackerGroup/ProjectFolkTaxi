package com.netcracker.services;

import com.netcracker.CustomException.NotFoundById;
import com.netcracker.entities.PaidJourney;
import com.netcracker.entities.StatusThank;
import com.netcracker.repositories.StatusThankRepository;
import com.netcracker.rootsearch.OptimalRouteFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusThankService {

    private final Logger LOG = LoggerFactory.getLogger(StatusThankService.class);

    private StatusThankRepository statusThankRepository;

    @Autowired
    public StatusThankService(
            StatusThankRepository statusThankRepository
    ) {
        this.statusThankRepository = statusThankRepository;
    }

    public StatusThank getStatusThankById(Long id) throws NotFoundById {
        LOG.debug("Status thank by id : {}", id );
        Optional<StatusThank> optionalStatusThank = statusThankRepository.findById(id);
        if (!optionalStatusThank.isPresent()) {
            LOG.error("Not found status thank by id : {}",  id);
            throw new NotFoundById();
        }
        return optionalStatusThank.get();
    }

}