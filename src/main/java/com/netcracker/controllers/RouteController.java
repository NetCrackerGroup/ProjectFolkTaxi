package com.netcracker.controllers;

//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
import com.netcracker.DTO.LoggedUserDto;
import com.netcracker.entities.Route;
import com.netcracker.services.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

//    @GetMapping("/test")
//    public ResponseEntity getUserDetails() {
//        String obj =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        Gson gson = new Gson();
//        LoggedUserDto loggedUserDto = gson.fromJson( obj, LoggedUserDto.class);
//        Map<String, Object> resonse = new HashMap<>();
//        resonse.put("user", obj);
//        return ResponseEntity.ok(resonse);
//    }
    
}
