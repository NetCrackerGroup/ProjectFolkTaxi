package com.netcracker.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.ScheduleDto;
import com.netcracker.DTO.UserDto;
import com.netcracker.entities.Route;
import com.netcracker.entities.Schedule;
import com.netcracker.entities.User;
import com.netcracker.rootsearch.InfoAboutRoute;
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
import java.util.Map;
import java.util.Set;
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
    
    @GetMapping("/closestRoutes/{startPoint}/{endPoint}/{stRadius}/{enRadius}/{departure}/{time}")
    public Collection<RouteDto> getClosestRoutes(@PathVariable(value="startPoint") String startPoint,
    		                                     @PathVariable(value="endPoint") String endPoint,	
    											 @PathVariable(value="stRadius") Integer stRadius,
    											 @PathVariable(value="enRadius") Integer enRadius,
    											 @PathVariable(value="departure") String departure,
    											 @PathVariable(value="time") String time){
    	
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
    	
    	
	      HashMap<InfoAboutRoute, Route> routes = routeService.getClosestRoutes(Double.parseDouble(startPoint.trim().split(",")[0]), 
    										 Double.parseDouble(startPoint.trim().split(",")[1]),
    										 Double.parseDouble(endPoint.trim().split(",")[0]),
    										 Double.parseDouble(endPoint.trim().split(",")[1]),
    										 stRadius, enRadius, dayOfWeek, time);
    	Collection<RouteDto> res = new ArrayList<RouteDto>();
    	RouteDto curDto;
    	Set<Map.Entry<InfoAboutRoute, Route>> set = routes.entrySet();
    	Integer minutesTillStart;
    	Integer timeNeededForWalk;
    	for(Map.Entry<InfoAboutRoute, Route> rt: set) {
    		curDto = routeMapper.toDto(rt.getValue()); 
    		
    		curDto.setDistance(rt.getKey().getDistance());
    		
    		rt.getKey().setTimeUntilStart(rt.getKey().getTimeUntilStart().replaceAll("-", ""));
    		minutesTillStart = Integer.parseInt(rt.getKey().getTimeUntilStart().split(":")[0]) * 60 + Integer.parseInt(rt.getKey().getTimeUntilStart().split(":")[1]);
    		timeNeededForWalk = rt.getKey().getDistance() / 83;
    		
    		/*
    		 * if person needs to walk less then 30 min and he will be in time -> 3 score
    		 * if person needs to walk more then 30 min and he will be in time -> 2 score
    		 * if person needs to walk more then 30 min and he will be late -> 1 score
    		 */
    		if(minutesTillStart > timeNeededForWalk) {
    			if(timeNeededForWalk < 30)
    				curDto.setOptimality(3);
    			else
    				curDto.setOptimality(2);
    		}
    		else
    			curDto.setOptimality(1);
    		
    		res.add(curDto);
    	}
    	
    	if(res.size() > 20) {
    		int count = res.size();
    		Iterator<RouteDto> it = res.iterator();
    		while(it.hasNext() && count > 20) {
    			if(it.next().getOptimality() == 1) {
    				it.remove();
    				count--;
    			}
    		}
    		it = res.iterator();
    		while(it.hasNext() && count > 20) {
    			if(it.next().getOptimality() == 2) {
    				it.remove();
    				count--;
    			}
    		}
    		it = res.iterator();
    		while(it.hasNext() && count > 20) {
    			it.remove();
				count--;
    			
    		}
    	}
    	
    	return res;
    }
    @GetMapping("/driver/{id}")
	public String getRouteDriver(@PathVariable(value="id") Long id) {
		return routeService.getRouteDriver(id);
	}
	@GetMapping("/driverId/{id}")
	public Long getDriverRoute(@PathVariable(value="id") Long id) {
		return routeService.getDriverRoute(id);
	}

	@PutMapping("/deletePassenger")
	public Map<String,RouteDto> deleteUserCompletely(@RequestParam(value="routeId")Long routeId, @RequestParam(value="userId")Long userId){
		LOG.info("[ deleteUserCompletely(userId : {})", userId);
		Map<String, RouteDto> response = new HashMap<String, RouteDto>() ;
		Route route;

		route=routeService.deleteUserCompletely(routeId,userId);
		response.put("route", routeMapper.toDto(route));

		LOG.info("]");
		return response;
	}

	@PostMapping("/userisdriverr")
	public Map<String, Boolean> checkUserIsDriver(@RequestParam(name = "routeId") Long routeId) {
		LOG.debug("#### checkUserIsModeraror #####");
		Boolean userIsDriver = routeService.CheckUserIsDriver(routeId);

		Map<String, Boolean>  response = new HashMap<String, Boolean>() {{
			put("isDriver", userIsDriver);
		}};
		return response;
	}
}
