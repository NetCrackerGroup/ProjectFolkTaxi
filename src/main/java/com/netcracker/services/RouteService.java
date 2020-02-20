package com.netcracker.services;

import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.entities.Route;
import com.netcracker.repositories.RouteRepository;
import com.netcracker.rootsearch.BasicRouteFinder;

@Service
public class RouteService {

	private static final Logger LOG = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    private RouteRepository routeRepository;
	
    public ArrayList<Route> getRoutesByCityId(Long cityId){

        ArrayList<Route> routes = null;  // routeRepository.findRouteByCityId(cityId);

        return routes;
    }
    
    public ArrayList<Route> getClosestRoutes(Double Xcord, Double Ycord, Integer radius, Calendar dep){
    	
    	BasicRouteFinder brf = new BasicRouteFinder();
    	return brf.findRoutes(Xcord, Ycord, radius, dep);
    	
    }
    
}
