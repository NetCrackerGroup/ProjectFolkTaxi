package com.netcracker.controllers;

import com.google.gson.Gson;
import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.ScheduleDto;
import com.netcracker.entities.Route;
import com.netcracker.entities.Schedule;
import com.netcracker.services.RouteMapper;
import com.netcracker.services.RouteService;
import com.netcracker.services.ScheduleMapper;
import org.locationtech.jts.geom.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        routeService.saveNewRoute(route, schedule);
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

        return routeMapper.toDto(route);
    }



    
}
