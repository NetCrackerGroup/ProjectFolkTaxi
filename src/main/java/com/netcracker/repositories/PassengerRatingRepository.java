package com.netcracker.repositories;

import com.netcracker.entities.PassengerRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRatingRepository extends CrudRepository<PassengerRating, Long> {
}

