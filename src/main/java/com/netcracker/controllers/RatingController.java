package com.netcracker.controllers;

import com.netcracker.repositories.UserRepository;
import com.netcracker.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

public class RatingController {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserRepository userRepository;

   /* @Autowired
    private RatingService usersService;

    @Autowired
    private RatingRepository userRepository;*/

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods" , "GET, PUT, POST, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    }

    @PostMapping
    public Double updateUserAvgDriverRating(@RequestParam (value = "driverRating") String newDriverMark){
        return null;
    }

    @PostMapping
    public Double updateUserAvgPassengerRating(@RequestParam (value = "passengerRating") String newPassengerMark){
        return null;
    }
}
