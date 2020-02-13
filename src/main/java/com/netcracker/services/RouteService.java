package com.netcracker.services;

import java.util.ArrayList;

import com.netcracker.DTO.RouteDto;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.netcracker.entities.Route;
import com.netcracker.repositories.RouteRepository;

@Service
public class RouteService {

	private static final Logger LOG = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;
	
    public ArrayList<Route> getRoutesByCityId(Long cityId){

        return routeRepository.findRouteByCity(cityId);
    }

    public void saveNewRoute(RouteDto routeDto) {
        LOG.info("[ saveNewRoute : {}", routeDto);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        Route route = new Route(routeDto.getRouteBegin(), routeDto.getRouteEnd(), routeDto.getPrice());
        User user = userRepository.findUserByEmail(userDetail.getUsername());
        route.setDriverId(user);
        route.setCity(user.getCityId());

        routeRepository.save(route);
    }
}
