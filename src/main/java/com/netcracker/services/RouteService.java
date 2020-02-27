package com.netcracker.services;

import java.util.ArrayList;

import com.netcracker.DTO.RouteDto;
import com.netcracker.entities.Schedule;
import com.netcracker.entities.User;
import com.netcracker.repositories.ScheduleRepository;
import com.netcracker.repositories.UserRepository;
//import com.vividsolutions.jts.geom.Coordinate;
//import com.vividsolutions.jts.geom.Point;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.GeometryFactory;
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
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;
	
    public ArrayList<Route> getRoutesByCityId(Long cityId){

        return routeRepository.findRouteByCity(cityId);
    }
    public Route getRoutesBy(Long routeId){

        return routeRepository.findRouteByRouteId(routeId);
    }
    public void saveNewRoute(Route route, Schedule schedule) {
        LOG.info("[ saveNewRoute : {}", route);

        // получение данных о сессии
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        route.setDriverId(user);
        route.setCity(user.getCityId());
        schedule.setRouteId(route);
        routeRepository.save(route);
        scheduleRepository.save(schedule);
    }
}
