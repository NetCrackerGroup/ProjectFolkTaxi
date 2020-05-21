package com.netcracker.repositories;

import com.netcracker.entities.DriverRateHistory;
import com.netcracker.entities.Journey;
import com.netcracker.entities.PassengerRateHistory;
import com.netcracker.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRateHistoryRepository extends CrudRepository<DriverRateHistory, Long> {
    DriverRateHistory findByJourneyIdAndRaterIdAndDriverId(Journey journeyId, Long raterId, User driverId);
}
