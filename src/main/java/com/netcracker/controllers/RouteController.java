package com.netcracker.controllers;

import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.mappers.RouteMapper;
import com.netcracker.entities.Route;
import com.netcracker.services.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

@RestController
@RequestMapping("routes")
//@CrossOrigin(origins="*", maxAge=3600)
public class RouteController {

	private static final Logger LOG = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private RouteService routeService;

    @Autowired
    RouteMapper routeMapper;
	
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

    @PostMapping("/add")
    public void saveNewRoute(@RequestBody RouteDto routeDto) {
        LOG.info("[ saveNewRoute : price  {}, routeBegin {}, routeEnd {}",
                routeDto.getPrice(), routeDto.getRouteBegin(), routeDto.getRouteEnd());
        LOG.debug("] (saveNewRoute )");
        routeService.saveNewRoute(routeDto);
    }
    @GetMapping("/{id}")
    public Route getRoutes(@PathVariable(value="id") Long id){
        LOG.info("[ getRoutesById : {}", id);

        Route routes = routeService.getRoutesBy(id);

        LOG.info("] return : {}", routes.toString());
        return routes;
    }
    
    @GetMapping("/closestRoutes/{address}/{radius}/{departure}")
    public Collection<RouteDto> getClosestRoutes(@PathVariable(value="address") String address,
    											 @PathVariable(value="radius") Integer radius,
    											 @PathVariable(value="departure") String departure){
    	
    	Calendar cal = new GregorianCalendar();
    	cal.set(Calendar.HOUR, Integer.parseInt(departure.split(":")[0]));
    	cal.set(Calendar.MINUTE, Integer.parseInt(departure.split(":")[1]));
    	
    	
    	Collection<Route> routes = routeService.getClosestRoutes(Double.parseDouble(address.split(" ")[0]), 
    										 Double.parseDouble(address.split(" ")[1]), radius, cal);
    	Collection<RouteDto> res = new ArrayList<RouteDto>();
    	for(Route rt: routes) {
    		res.add(routeMapper.toDto(rt));
    	}
    	return res;
    }
    
    
}
