package com.netcracker.services;

import com.netcracker.entities.City;
import com.netcracker.repositories.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private static final Logger LOG = LoggerFactory.getLogger(City.class);

    @Autowired
    private CityRepository cityRepository;


    public Long createNewCity(String cityName, String cityMap){
        LOG.debug("[ createCity(cityName : {}, cityMap : {}", cityName, cityMap);

        City city = new City(cityName, cityMap);
        cityRepository.save(city);

        LOG.debug("] (cityId : {})", city.getCityId());
        return city.getCityId();
    }

    public City getCityById(Long cityId){
        LOG.debug("[ getById(userId : {})", cityId);

        City city = cityRepository.findOne(cityId);

        LOG.debug("] (return : {})", city);
        return city;
    }
    public Iterable<City> getAllCity() {
        LOG.debug("[ getAllCity()");
        Iterable<City> cities = cityRepository.findAll();
        LOG.debug("] (getAllCity )");
        return cities;
    }


}
