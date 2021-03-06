package com.netcracker.services;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.netcracker.entities.*;

import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.mappers.RouteMapper;
import com.netcracker.repositories.*;
import com.netcracker.services.Channels.ApplicationSenderService;
import com.netcracker.services.Channels.EmailServiceImpl;
import com.netcracker.services.Channels.FillInfoContent;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.netcracker.entities.Route;
import com.netcracker.repositories.RouteRepository;
import com.netcracker.rootsearch.InfoAboutRoute;

import javax.management.Query;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.Collection;

import com.netcracker.rootsearch.OptimalRouteFinder;

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
    private OptimalRouteFinder srf;

    @Autowired
    JourneyRepository journeyRepository;
    
    @Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	DataSource dataSource;

	@Autowired
    private NotificationService notificationService;

	@Autowired
    private InfoContentService infoContentService;

	@Autowired
    private ApplicationSenderService applicationSenderService;

	@Autowired
    private YandexService yandexService;

    
    
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

    public boolean joinToRoute(long id) throws Exception {
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
            Map<String, String> maps = new HashMap<String, String>();
            User driver = route.getDriverId();
            maps.put("userRoute", user.getFio());
            FillInfoContent fillInfoContent = new FillInfoContent(maps);
            notificationService.notify(
                    infoContentService.getInfoContentByKey("user_entered_route"),
                    applicationSenderService,
                    driver,
                    fillInfoContent
            );
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
    		Integer stRadius, Integer enRadius, Integer dayOfWeek, String time, Long groupId){
    	HashMap<InfoAboutRoute, Route> routes = srf.findRoutes(stXcord, stYcord, enXcord, enYcord, stRadius, enRadius, dayOfWeek, time, groupId);
    	
    	return routes;  
    }
    public String getRouteDriver(Long id) {
        Route route = routeRepository.findRouteByRouteId(id);
        return route.getDriverId().getImage();
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<Route> getRandomRoutes(){
    	
    	int min = 8;
    	int max = 1000;
    	
    	ArrayList<Route> list = new ArrayList<Route>();
    	ArrayList<Long> temp = new ArrayList<Long>();
    	
    	int rand = min + (int)(Math.random() * ((max - min) + 1));
    		
		List<Long> results = jdbc.query(
                "SELECT S.Route_Id "
                + "FROM SCHEDULES S "
                + "WHERE ? & CAST(S.schedule_day AS INT) <> 0" 
                + "  AND (S.time_of_journey - CAST( NOW() AS time)) < CAST( ? AS interval)" 
       		    + "  AND (CAST( NOW() AS time) - S.time_of_journey) < CAST( ? AS interval);", 
       		 new PreparedStatementSetter() {
	    	  		public void setValues(PreparedStatement preparedStatement) throws SQLException{
	    	  			
	    	  			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	  			LocalDateTime now = LocalDateTime.now();
	    	  			
	    	  			Calendar cal = new GregorianCalendar();
	    	  	    	cal.set(Calendar.YEAR , Integer.parseInt(dtf.format(now).split("\\-")[2]));
	    	  	    	cal.set(Calendar.MONTH, Integer.parseInt(dtf.format(now).split("\\-")[1]));
	    	  	    	cal.set(Calendar.DAY_OF_MONTH , Integer.parseInt(dtf.format(now).split("\\-")[0]));
	    	  	    	
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
	    	  			
	    	  			preparedStatement.setInt(1, dayOfWeek);
	    	  			preparedStatement.setString(2, "06:00");
	    	  			preparedStatement.setString(3, "06:00");
	    	  		}
	      		},
       		    new RowMapper<Long>() {
                    @Override
                    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getLong("route_id");
                    }
                });
		temp.addAll(0, results);
		
		for(int i = 0; i < 3; i++)
			list.add(routeRepository.findRouteByRouteId(temp.get(i)));
	
    	return list;
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

    public boolean startJourney(Long routeId) {
        LocalDate date = LocalDate.now();
        Route currentRoute = routeRepository.findRouteByRouteId(routeId);
        Collection<User> users = currentRoute.getUsers();
        Journey currentJourney =  journeyRepository.getJourneyByRouteAndDate(currentRoute, date);
        if (currentJourney == null) {
            Journey journey = new Journey(date, new ArrayList<User>(), currentRoute, currentRoute.getDriverId(), true, false);
            journey.setUsers(users);
            journeyRepository.save(journey);
            return true;
        }
        return false;
    }

    public void endJourney(Long routeId) throws Exception {
        LocalDate date = LocalDate.now();
        Route currentRoute = routeRepository.findRouteByRouteId(routeId);
        Journey journey =  journeyRepository.getJourneyByRouteAndDate(currentRoute, date);
        journey.setIsfinished(true);
        this.yandexService.thanksPasseger(currentRoute, journey);
        journeyRepository.save(journey);
    }
}