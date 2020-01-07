package com.netcracker.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.entities.Route;
import com.netcracker.repositories.RouteRepository;

@Service
public class RouteService {

	private static final Logger LOG = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    private RouteRepository routeRepository;
	
    public ArrayList<Route> getRoutesByCityId(Long cityId){

        ArrayList<Route> routes = routeRepository.findRouteByCity(cityId);

        return routes;
    }
}
