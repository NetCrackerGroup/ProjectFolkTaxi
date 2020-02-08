package com.netcracker.Controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import com.netcracker.services.ReportService;
import com.netcracker.services.RouteService;

@RestController
@RequestMapping("routes")
public class RouteController {

	private static final Logger LOG = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private RouteService routeService;
    
    @GetMapping ("")
    String home ()
    {
        return "routes";
    }
	
    @GetMapping("/route/byCity/{id}")
    public ArrayList<Route> getRoutesByCityId(@PathVariable(value="id") Long id){
        LOG.info("[ getRoutesByCityId : {}", id);

        ArrayList<Route> routes = routeService.getRoutesByCityId(id);

        LOG.info("] return : {}", routes.toString());
        return routes;
    }
    
}
