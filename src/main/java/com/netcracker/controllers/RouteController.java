package com.netcracker.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import com.google.gson.Gson;
import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.ScheduleDto;
import com.netcracker.DTO.UserDto;
import com.netcracker.entities.Route;
import com.netcracker.entities.Schedule;
import com.netcracker.entities.User;
import com.netcracker.services.*;
import org.locationtech.jts.geom.Point;

import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.mappers.RouteMapper;
import com.netcracker.DTO.mappers.ScheduleMapper;
import com.netcracker.DTO.mappers.UserMapper;
import com.netcracker.entities.Route;
import com.netcracker.services.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.spring.web.json.Json;

import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import com.netcracker.services.ReportService;
import com.netcracker.services.RouteService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
	private RouteMapper routeMapper;

	@Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private RouteService routeService;

    @Autowired
    private UserMapper userMapper;
  
    @Autowired
    private ChatsService chatsService;
	
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
    public void saveNewRoute(@RequestParam(name = "postUser") String routeDto2, @RequestParam(required = false, name = "selectedDays") String scheduleDto2) throws ParseException {
        Gson gson = new Gson();
        RouteDto routeDto = gson.fromJson(routeDto2, RouteDto.class);
        ScheduleDto scheduleDto = gson.fromJson(scheduleDto2, ScheduleDto.class);
        LOG.info("[ saveNewRoute : price  {}, routeBegin {}, routeEnd {}",
                routeDto.getPrice(), routeDto.getRouteBegin(), routeDto.getRouteEnd());
        Route route =  routeMapper.toEntity(routeDto);
        //доделать расписание , посмотреть как сделать время, по возможности раздилить dto
        Schedule schedule = scheduleMapper.toEntity(scheduleDto);
        schedule.setScheduleDay(Integer.parseInt(scheduleDto.getScheduleDay(), 2));

        routeService.saveNewRoute(route, schedule);
        chatsService.createNewChat(route, null);
        LOG.debug("] (saveNewRoute )");
    }
//    @PostMapping("/addOne")
//    public void saveOneRoute(@RequestBody RouteDto routeDto) {
//        Route route = routeMapper.toEntity(routeDto);
//        routeService.saveNewOneRoute(route);
//
//    }
    @GetMapping("/{id}")
    public RouteDto getRoutes(@PathVariable(value="id") int id){
        LOG.info("[ getRoutesById : {}", id);

        Route route = routeService.getRoutesBy((long) id);

        LOG.info("] return : {}", route.toString());
        RouteDto routeDto = routeMapper.toDto(route);
        //routeDto.setDriverRating(routeService.getDriverRatingById(id));
        return routeDto;
    }
    @PostMapping("/join")
    public boolean joinToRoute(@RequestParam(name = "id") String id) {
        LOG.info("[ joinToRoute : {}", id);
        return routeService.joinToRoute(Long.parseLong(id));
    }
    @GetMapping("/users/{id}")
    public Collection<UserDto> getAllUserRoute(@PathVariable(name = "id") long id) {
        LOG.info("[ joinToRoute : {}", id);
        Collection<User> users = routeService.getAllUserRoute(id);
        Collection<UserDto> usersDTO = new ArrayList<>();
        for (User user:
             users) {
            usersDTO.add(userMapper.toDto(user));
        }
        return usersDTO;
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
