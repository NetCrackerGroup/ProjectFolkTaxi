package com.netcracker.services;

import com.netcracker.dto.UserDto;
import com.netcracker.entities.City;
import com.netcracker.entities.Group;
import com.netcracker.entities.Route;
import com.netcracker.repositories.CityRepository;
import com.netcracker.security.SecurityRole;
import org.omg.CORBA.WStringSeqHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import sun.rmi.runtime.Log;

import java.util.*;

@Service
public class UsersService {
    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private CityRepository cityRepository;

    /**
     *
     * @param fio
     * @param email
     * @param phoneNumber
     * @return
     */
    public Long createNewUser(String fio, String email, String phoneNumber, City city, String password, String securityRole){
        LOG.debug("[ createUser(fio : {}, email : {}, phoneNumber : {}", fio, email, phoneNumber);

        User user = new User(fio, email, phoneNumber, city, password, securityRole);
        usersRepository.save(user);

        LOG.debug("] (userId : {})", user.getUserId());
        return user.getUserId();
    }

    public Long saveNewUser(UserDto userDto) {
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
    public User getUserByEmail(String email){
        LOG.debug("[ getByEmail(email : {})", email);

        User user = usersRepository.findUserByEmail(email);

        LOG.debug("] (return : {})", user);
        return user;
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
        User user = usersRepository.findUserByFio(fio);
        return user;
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
    public User getUserById(Long userId) {
        System.out.println("`2223");
        Optional<User> user = usersRepository.findById(userId);
        return user.isPresent() ? user.get() : null;
    }


}
