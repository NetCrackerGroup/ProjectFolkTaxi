package com.netcracker.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    
    @GetMapping("/closestRoutes/{startPoint}/{endPoint}/{stRadius}/{enRadius}/{departure}")
    public Collection<RouteDto> getClosestRoutes(@PathVariable(value="startPoint") String startPoint,
    		                                     @PathVariable(value="endPoint") String endPoint,	
    											 @PathVariable(value="stRadius") Integer stRadius,
    											 @PathVariable(value="enRadius") Integer enRadius,
    											 @PathVariable(value="departure") String departure){
    	
    	Calendar cal = new GregorianCalendar();
    	cal.set(Calendar.YEAR , Integer.parseInt(departure.split("\\.")[2]));
    	cal.set(Calendar.MONTH, Integer.parseInt(departure.split("\\.")[1]));
    	cal.set(Calendar.DAY_OF_MONTH , Integer.parseInt(departure.split("\\.")[0]));
    	
    	
    	Integer dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	      
	      switch(dayOfWeek) {
		      case(1):{
		    	  dayOfWeek = 1;
		    	  break;
		      }
		      case(2):{
		    	  dayOfWeek = 64;
		    	  break;
		      }
		      case(3):{
		    	  dayOfWeek = 32;
		    	  break;
		      }
		      case(4):{
		    	  dayOfWeek = 16;
		    	  break;
		      }
		      case(5):{
		    	  dayOfWeek = 8;
		    	  break;
		      }
		      case(6):{
		    	  dayOfWeek = 4;
		    	  break;
		      }
		      case(7):{
		    	  dayOfWeek = 2;
		    	  break;
		      }
	      }
    	
    	
    	Collection<Route> routes = routeService.getClosestRoutes(Double.parseDouble(startPoint.trim().split(",")[0]), 
    										 Double.parseDouble(startPoint.trim().split(",")[1]),
    										 Double.parseDouble(endPoint.trim().split(",")[0]),
    										 Double.parseDouble(endPoint.trim().split(",")[1]),
    										 stRadius, enRadius, dayOfWeek);
    	Collection<RouteDto> res = new ArrayList<RouteDto>();
    	for(Route rt: routes) {
    		res.add(routeMapper.toDto(rt));
    	}
    	return res;
    }
    
    
}
