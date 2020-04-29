package com.netcracker.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import com.netcracker.entities.Route;
import com.netcracker.entities.Schedule;
import java.util.Collection;
import java.util.HashMap;

import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.mappers.RouteMapper;
import com.netcracker.entities.User;
import com.netcracker.repositories.RouteRepository;
import com.netcracker.repositories.ScheduleRepository;
import com.netcracker.repositories.UserRepository;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.netcracker.entities.Route;
import com.netcracker.repositories.RouteRepository;
import com.netcracker.rootsearch.BasicRouteFinder;
import com.netcracker.rootsearch.InfoAboutRoute;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.netcracker.rootsearch.SecondRouteFinder;

@Service
public class RouteService {

	private static final Logger LOG = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;
	
    @Autowired
    private SecondRouteFinder srf;
    
    
    
    public ArrayList<Route> getRoutesByCityId(Long cityId){
        return routeRepository.findRouteByCity(cityId);
    }
    
    public Route getRoutesBy(Long routeId){
        return routeRepository.findRouteByRouteId(routeId);
    }
    public void saveNewRoute(Route route, Schedule schedule) {
        LOG.info("[ saveNewRoute : {}", route);

        // получение данных о сессии
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        route.setDriverId(user);
        route.setCity(user.getCityId());
        schedule.setRouteId(route);
        Collection<User> usersInGroup = new ArrayList<>();
        usersInGroup.add(user);
        route.setUsers(usersInGroup);
        routeRepository.save(route);
        scheduleRepository.save(schedule);

    }

    public void saveNewOneRoute(Route route) {
        LOG.info("[ saveNewOneRoute : {}", route);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        route.setDriverId(user);
        route.setCity(user.getCityId());
        routeRepository.save(route);
    }
    public boolean joinToRoute(long id) {
        LOG.info("[ joinToRoute : {}", id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        Route route =  routeRepository.findRouteByRouteId(id);
        Collection<Long> idNotUsers = new ArrayList<Long>();
        boolean contain = false;
        if(route.getNotUsers()!=null) {
            for (User notUser : route.getNotUsers()) {
                idNotUsers.add(notUser.getUserId());
            }

            if (idNotUsers.contains(user.getUserId())) {
                contain = true;
            }
        }

        for (Route item:
             user.getRoutes()) {
            if (item.getRouteId() == id) return false;
        }
        if (route.getCountOfPlaces() == 0) {
            return false;
        }else if(contain) return false; else{
            route.setCountOfPlaces(route.getCountOfPlaces() - 1);
            Collection<User> usersInGroup =  route.getUsers();
            usersInGroup.add(user);
            route.setUsers(usersInGroup);
            routeRepository.save(route);
            return true;
        }
    }
    public Double getDriverRatingById(long id) {
        Route route = routeRepository.findRouteByRouteId(id);
        return route.getDriverId().getDriverRating();
    }

    public Collection<User> getAllUserRoute(long id) {
        return routeRepository.findRouteByRouteId(id).getUsers();
    }
    
    public HashMap<InfoAboutRoute, Route> getClosestRoutes(Double stXcord, Double stYcord, Double enXcord, Double enYcord,
    		Integer stRadius, Integer enRadius, Integer dayOfWeek, String time){
    	HashMap<InfoAboutRoute, Route> routes = srf.findRoutes(stXcord, stYcord, enXcord, enYcord, stRadius, enRadius, dayOfWeek, time);
    	
    	return routes;  
    }
    public String getRouteDriver(Long id) {
        Route route = routeRepository.findRouteByRouteId(id);
        return route.getDriverId().getImage();

    }
    public Long getDriverRoute(Long id) {
        Route route = routeRepository.findRouteByRouteId(id);
        return route.getDriverId().getUserId();
    }

    public Route deleteUserCompletely (Long routeId,Long userId) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        Route route = routeRepository.findById(routeId).orElse(null);
        Long driverId = route.getDriverId().getUserId();

        LOG.debug("[ driverId( :{}", driverId);

        boolean driver = false;

        LOG.debug("[ user( :{}", user.getUserId());

        if (driverId == user.getUserId()) {
            driver = true;
        }

        LOG.debug("[ driver_boolean( :{}", driver);
        if (driver) {
            Collection<User> usersNotInRoute = route.getNotUsers();
            Collection<User> users = route.getUsers();
            User deleteUser = userRepository.findUserByUserId(userId);
            usersNotInRoute.add(deleteUser);
            users.remove(deleteUser);
            route.setUsers(users);
            route.setNotUsers(usersNotInRoute);
            route.setCountOfPlaces(route.getCountOfPlaces()+1);
        }
        LOG.debug("[ users( :{}", route.getUsers());

        routeRepository.save(route);


        return route;
    }

    public boolean CheckUserIsDriver(Long routeId){
        boolean driver = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        Route route = routeRepository.findById(routeId).orElse(null);

        if(user.getUserId() == route.getDriverId().getUserId()) {
             driver = true;
        }
        LOG.debug("[ Isdriver( :{}", driver);

        return driver;

    }
}

