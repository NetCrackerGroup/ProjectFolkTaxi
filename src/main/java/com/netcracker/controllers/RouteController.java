package com.netcracker.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import com.netcracker.services.ReportService;
import com.netcracker.services.RouteService;

@RestController
@RequestMapping("routes")
//@CrossOrigin(origins="*", maxAge=3600)
public class RouteController {

	private static final Logger LOG = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private RouteService routeService;
    
    @GetMapping ("")
    String home ()
    {
        return "routes";
    }
	
    @GetMapping("/route/{id}")
    public ArrayList<Route> getRoutesById(@PathVariable(value="id") Long id){
        LOG.info("[ getRoutesById : {}", id);

        ArrayList<Route> routes = routeService.getRoutesByCityId(id);

        LOG.info("] return : {}", routes.toString());
        return routes;
    }
    
}
