package com.netcracker.services;

import com.netcracker.entities.City;
import com.netcracker.entities.User;
import com.netcracker.repositories.CityRepository;
import com.netcracker.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CitiesService {

    private static final Logger LOG = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;


    public City getCityById(Long cityId){

        Optional<City> city = cityRepository.findById(cityId);

        return city.isPresent() ? city.get() : null;
    }
}
