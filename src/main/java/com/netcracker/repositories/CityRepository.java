package com.netcracker.repositories;

import com.netcracker.entities.City;
import com.netcracker.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    City findCityByCityName(String cityName);
}

