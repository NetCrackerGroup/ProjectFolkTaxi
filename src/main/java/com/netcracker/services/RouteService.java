package com.netcracker.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.mappers.RouteMapper;
import com.netcracker.entities.User;
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
import com.netcracker.rootsearch.BasicRouteFinder;
import com.netcracker.rootsearch.SecondRouteFinder;

@Service
public class RouteService {

	private static final Logger LOG = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;
	
    @Autowired
    private SecondRouteFinder srf;
    
    
    
    public ArrayList<Route> getRoutesByCityId(Long cityId){

        ArrayList<Route> routes = null;  // routeRepository.findRouteByCityId(cityId);
     //   return routeRepository.findRouteByCity(cityId);
        return routes;
    }
    
    public Route getRoutesBy(Long routeId){
    	
        return routeRepository.findRouteByRouteId(routeId);
    }
    public void saveNewRoute(RouteDto routeDto) {
        LOG.info("[ saveNewRoute : {}", routeDto);
        // получение данных о сессии
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordBegin = new Coordinate(routeDto.getRouteBegin()[0], routeDto.getRouteBegin()[1]);
        Coordinate coordEnd = new Coordinate(routeDto.getRouteEnd()[0], routeDto.getRouteEnd()[1]);
        Point pointBegin = geometryFactory.createPoint(coordBegin);
        Point pointEnd = geometryFactory.createPoint(coordEnd);

        Route route = new Route(pointBegin, pointEnd, routeDto.getPrice());

        route.setDriverId(user);
        route.setCity(user.getCityId());
        routeRepository.save(route);
    }
    
    public Collection<Route> getClosestRoutes(Double stXcord, Double stYcord, Double enXcord, Double enYcord,
    		Integer stRadius, Integer enRadius, Integer dayOfWeek){
    	Collection<Route> routes = srf.findRoutes(stXcord, stYcord, enXcord, enYcord, stRadius, enRadius, dayOfWeek);
    	
    	return routes;  
    }
    
}
