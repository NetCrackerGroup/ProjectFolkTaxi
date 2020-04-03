package com.netcracker.controllers;

import com.netcracker.entities.City;
import com.netcracker.services.CitiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cities")
public class CityController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private CitiesService cityService;

    @GetMapping("")
    String home ()
    {
        return "cities";
    }

    @GetMapping("/{id}")
    City getCityById (@PathVariable(name = "id") String id){
        LOG.info("[ getCityById : {}", id);

        City city = cityService.getCityById(Long.decode(id));

        return city;
    }
}
