package com.netcracker.Controllers;

import com.google.gson.Gson;
import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.ScheduleDto;
import com.netcracker.DTO.UserDto;
import com.netcracker.entities.Route;
import com.netcracker.entities.Schedule;
import com.netcracker.entities.User;
import com.netcracker.services.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
        chatsService.createNewChat(route,null);

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





}