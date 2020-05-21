package com.netcracker.repositories;

import com.netcracker.entities.Journey;
import com.netcracker.entities.PassengerRateHistory;
import com.netcracker.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRateHistoryRepository extends CrudRepository<PassengerRateHistory, Long> {

    PassengerRateHistory findByJourneyIdAndRaterIdAndPassengerId(Journey journeyId, Long raterId, User passengerId);
}
