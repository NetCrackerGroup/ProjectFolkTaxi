package com.netcracker.repositories;

import com.netcracker.entities.Route;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {
	
//	ArrayList<Route> findRouteByCityId(Long cityId);
	Route findRouteByRouteId(Long routeId);
	
}

