package com.netcracker.Controllers;


import com.netcracker.entities.City;
import com.netcracker.entities.User;
import com.netcracker.services.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("city")
public class CityController {

    @Autowired
    private CityService cityService;
    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @GetMapping("")
    public Iterable<City> getAllUsers(){
        LOG.info("[ getAllUsers");

        Iterable<City> cities = cityService.getAllCity();

        LOG.info("] (return : {})", cities);
        return cities;
    }

    @GetMapping("/{id}")
    public City getUserByid(@PathVariable(name = "id") Long id) {
        LOG.info("[getUserByid : {}", id);
        System.out.println("`123123");
        City city = cityService.getCityById(id);
        LOG.info("] return : {}", city);
        return city;
    }

    @PostMapping("")
    public Long createNewСity(@RequestParam  String cityName, @RequestParam  String cityMap){
        LOG.debug("[ createUser(cityName : {}, cityMap : {}", cityName, cityMap);
        Long cityId = cityService.createNewCity( cityName,  cityMap);
        LOG.debug("] (userId : {})", cityId);
        return cityId;
    }



}
