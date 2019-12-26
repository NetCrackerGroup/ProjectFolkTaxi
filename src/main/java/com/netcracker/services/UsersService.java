package com.netcracker.services;

import com.netcracker.entities.City;
import com.netcracker.entities.Group;
import com.netcracker.entities.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;

import java.util.*;

@Service
public class UsersService {
    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UserRepository usersRepository;

    /**
     *
     * @param fio
     * @param email
     * @param phoneNumber
     * @return
     */
 /*   public Long createNewUser(String fio, String email, String phoneNumber, City city){
        LOG.debug("[ createUser(fio : {}, email : {}, phoneNumber : {}", fio, email, phoneNumber);

        User user = new User(fio, email, phoneNumber, city);
        usersRepository.save(user);

        LOG.debug("] (userId : {})", user.getUserId());
        return user.getUserId();
    }*/

    /**
     *
     * @param email
     * @return
     */
   /* public User getUserByEmail(String email){
        LOG.debug("[ getByEmail(email : {})", email);

        User user = usersRepository.findUserByEmail(email);

        LOG.debug("] (return : {})", user);
        return user;
    }

    public Map<Class, Collection<?>> getGroupAndRoute(Long userId) {
        User user = usersRepository.findOne(userId);

            Collection<Group> userGroup = user.getGroups();
            Collection<Route> usersRoute = user.getRoutes();
            HashMap<Class, Collection<?>> map = new HashMap<>();
            map.put(Group.class, userGroup);
            map.put(Route.class, usersRoute);

        return map;

    }

    public Collection<Group> getUserGroup(Long userId) {
        User user = usersRepository.findOne(userId);
        return user.getGroups();
    }
    public Collection<Route> getUserRoute(Long userId) {
        User user = usersRepository.findOne(userId);
        return user.getRoutes();
    }

    public Double getRating(Long userId, Boolean isPassenger) {
        User user = usersRepository.findOne(userId);
        return isPassenger? user.getPassengerRating(): user.getDriverRating();
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
   /* public User getUserById(Long userId) {
        System.out.println("`2223");
        return usersRepository.findOne(userId);
    }*/
}
