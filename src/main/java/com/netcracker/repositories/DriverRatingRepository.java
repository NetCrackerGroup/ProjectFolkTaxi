package com.netcracker.repositories;

import com.netcracker.entities.DriverRating;
import com.netcracker.entities.User;

public class DriverRatingRepository extends AbstractRepository<DriverRating> {
    public DriverRatingRepository() {
        super(DriverRating.class);
    }
}
