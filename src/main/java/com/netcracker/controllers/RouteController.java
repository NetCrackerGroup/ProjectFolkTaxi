package com.netcracker.controllers;

import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.UserDto;
import com.netcracker.entities.Route;
import com.netcracker.entities.Schedule;
import com.netcracker.services.RouteMapper;
import com.netcracker.services.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("routes")
//@CrossOrigin(origins="*", maxAge=3600)
public class RouteController {

	private static final Logger LOG = LoggerFactory.getLogger(RouteController.class);

	@Autowired
	private RouteMapper routeMapper;

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

    @PostMapping("/add")
    public void saveNewRoute(@RequestBody RouteDto routeDto) throws ParseException {
        LOG.info("[ saveNewRoute : price  {}, routeBegin {}, routeEnd {}",
                routeDto.getPrice(), routeDto.getRouteBegin(), routeDto.getRouteEnd());
        Route route =  routeMapper.toEntity(routeDto);
        //доделать расписание , посмотреть как сделать время, по возможности раздилить dto
        SimpleDateFormat sfd = new SimpleDateFormat("HH:mm");
        Schedule schedule = new Schedule();
        schedule.setTimeOfJourney(sfd.parse(routeDto.getTime()));
        StringBuilder builder = new StringBuilder();
        for(int s : routeDto.getSelectedDays()) {
            builder.append(s);
        }
        String str = builder.toString();
        schedule.setScheduleDay(str);
        routeService.saveNewRoute(route, schedule);
        LOG.debug("] (saveNewRoute )");
    }
    @GetMapping("/{id}")
    public Route getRoutes(@PathVariable(value="id") Long id){
        LOG.info("[ getRoutesById : {}", id);

        Route routes = routeService.getRoutesBy(id);

        LOG.info("] return : {}", routes.toString());
        return routes;
    }


    
}
