package com.netcracker.repositories;

import com.netcracker.entities.Journey;
import com.netcracker.entities.PaidJourney;
import org.springframework.data.repository.CrudRepository;
import com.netcracker.entities.User;

import java.util.Collection;
import java.util.Optional;

public interface PaidJourneyRepository extends CrudRepository<PaidJourney, Long> {
    Collection<PaidJourney> findAllByUser(User user);
    Optional<PaidJourney> findPaidJourneyByUserAndJourney(User user, Journey journey);
}
