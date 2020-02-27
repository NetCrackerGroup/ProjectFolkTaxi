package com.netcracker.services;

import com.netcracker.DTO.GroupDto;
import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.UserDto;
import com.netcracker.DTO.UserSecDto;
import com.netcracker.DTO.mappers.GroupMapper;
import com.netcracker.DTO.mappers.RouteMapper;
import com.netcracker.DTO.mappers.UserMapper;
import com.netcracker.entities.City;
import com.netcracker.entities.Group;
import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import com.netcracker.repositories.CityRepository;
import com.netcracker.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService {
    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);

    private UserRepository usersRepository;
    private UserMapper userMapper;
    private RouteMapper routeMapper;
    private GroupMapper groupMapper;
    
    @Autowired
    public UsersService(    UserRepository usersRepository,
                            UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    @Autowired
    private CityRepository cityRepository;


    public Long createNewUser(String fio, String email, String phoneNumber, City city, String password, String securityRole){
        LOG.debug("[ createUser(fio : {}, email : {}, phoneNumber : {}", fio, email, phoneNumber);

        User user = new User(fio, email, phoneNumber, city, password, securityRole);
        usersRepository.save(user);

        LOG.debug("] (userId : {})", user.getUserId());
        return user.getUserId();
    }

    public Long saveNewUser(UserSecDto userDto) {
        LOG.debug("[ saveNewUser(fio : {}", userDto);
        Optional<City> city = cityRepository.findById((long) userDto.getCityId());
        User user = userDto.toUser(city.get());
        usersRepository.save(user);

        LOG.debug("] (userId : {})", user.getUserId());
        return user.getUserId();
    }

    /**
     *
     * @param email
     * @return
     */
    public UserDto getUserByEmail(String email){
        LOG.debug("[ getByEmail(email : {})", email);

        User user = usersRepository.findUserByEmail(email);

        LOG.debug("] (return : {})", user);
        return userMapper.toDto(user);
    }

    public Map<Class, Collection<?>> getGroupAndRoute(Long userId) {
        Optional<User> possible_user = usersRepository.findById(userId);
        HashMap<Class, Collection<?>> map = null;
        if( possible_user.isPresent() ){
            User user =  possible_user.get();
            Collection<Group> userGroup = user.getGroups();
            Collection<Route> usersRoute = user.getRoutes();
            map = new HashMap<>();
            map.put(Group.class, userGroup);
            map.put(Route.class, usersRoute);
        }
        return map;
    }
    public User getUserByFIO(String fio) {
        LOG.debug("getUserByUsername(username: {})", fio);
        return usersRepository.findUserByFio(fio);
    }

    public Collection<Group> getUserGroup(Long userId) {
        Optional<User> possible_user = usersRepository.findById(userId);
        return possible_user.isPresent() ? possible_user.get().getGroups() : null;
    }

    public Collection<Route> getUserRoute(Long userId) {
        Optional<User> possible_user = usersRepository.findById(userId);
        return possible_user.isPresent() ?
                    possible_user.get().getRoutes() :
                    null;
    }

    public Collection<GroupDto> getUserGroupsByEmail(String userEmail) {
        User possibleUser = usersRepository.findUserByEmail(userEmail);
        ArrayList<GroupDto> res = new ArrayList<GroupDto>();
        
        if(possibleUser != null) {
        	Collection<Group> groups = possibleUser.getGroups();
        	
        	for(Group gr: groups) {
        		res.add(groupMapper.toDto(gr));
        	}
        	return res;
        }
        return null;
    }
    public Collection<RouteDto> getUserRoutesByEmail(String userEmail) {
        User possibleUser = usersRepository.findUserByEmail(userEmail);
        ArrayList<RouteDto> res = new ArrayList<RouteDto>();
        
        if(possibleUser != null) {
        	Collection<Route> routes = possibleUser.getRoutes();
        	
        	for(Route rt: routes) {
        		res.add(routeMapper.toDto(rt));
        	}
        	return res;
        }
        return null;
    }
    
    public Double getRating(Long userId, Boolean isPassenger) {
        Optional<User> possible_user = usersRepository.findById(userId);
        return possible_user.isPresent() ?
                isPassenger ?
                        possible_user.get().getPassengerRating() :
                        possible_user.get().getDriverRating() :
                null;
    }

    /**
     *
     * @return
     */
    public Iterable<User> getAllUsers(){
        LOG.debug("[ getAllUsers");

        Iterable<User> users = usersRepository.findAll();

        LOG.debug("] (return : {})", users);
        return users;
    }
    public UserDto getUserById(Long userId) {
        System.out.println("`2223");
        Optional<User> user = usersRepository.findById(userId);
        return user.isPresent() ? userMapper.toDto(user.get()) : null;
    }
    
    public String getUserEmail() {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = usersRepository.findUserByEmail(userDetail.getUsername());
    	
        return user.getEmail();
    }
}
