package com.netcracker.repositories;

import com.netcracker.entities.Journey;
import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface JourneyRepository extends CrudRepository<Journey, Long> {

    Journey getJourneyByRouteAndDate(Route route, LocalDate date);

}
