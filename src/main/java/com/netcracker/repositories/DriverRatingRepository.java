package com.netcracker.repositories;

import com.netcracker.entities.DriverRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRatingRepository extends CrudRepository<DriverRating, Long> {
}
