package com.netcracker.controllers;

import com.netcracker.entities.City;
import com.netcracker.entities.Group;
import com.netcracker.entities.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.services.UsersService;
import com.netcracker.entities.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UsersController {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @PostMapping("")
    public Long createNewUser(@RequestParam  String fio, @RequestParam  String email, @RequestParam  String phoneNumber, @RequestParam City city){
        LOG.debug("[ createUser(fio : {}, email : {}, phoneNumber : {}", fio, email, phoneNumber);
        Long userId = usersService.createNewUser(fio, email, phoneNumber, city);

        LOG.debug("] (userId : {})", userId);
        return userId;
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable(value="email") String email){
        LOG.info("[ getUserByEmail : {}", email);

        User user = usersService.getUserByEmail(email);

        LOG.info("] return : {}", user);
        return user;
    }

    @GetMapping("")
    public Iterable<User> getAllUsers(){
        LOG.info("[ getAllUsers");

        Iterable<User> users = usersService.getAllUsers();

        LOG.info("] (return : {})", users);
        return users;
    }

    @GetMapping("/{id}")
    public User getUserByid(@PathVariable(name = "id") Long id) {
        LOG.info("[getUserByid : {}", id);
        System.out.println("`123123");
        User user = usersService.getUserById(id);
        LOG.info("] return : {}", user);
        return user;
    }

    @GetMapping("/{id}/groups")
    public Collection<Group> getUserGroup(@PathVariable(name = "id") Long id) {
        LOG.info("[getUserGroup : {}", id);
        Collection<Group> group = usersService.getUserGroup(id);
        LOG.info("] return : {}", group);
        return group;
    }
    @GetMapping("/{id}/routes")
    public Collection<Route> getUserRoutes(@PathVariable(name = "id") Long id) {
        LOG.info("[getUserRoutes : {}", id);
        Collection<Route> routes = usersService.getUserRoute(id);
        LOG.info("] return : {}", routes);
        return routes;
    }
    @GetMapping("/{id}/routesAndGroups")
    public Map<Class, Collection<?>> getUserRoutesGroupes(@PathVariable(name = "id") Long id) {
        LOG.info("[getUserRoutesGroupes : {}", id);
        Map<Class, Collection<?>> map = usersService.getGroupAndRoute(id);
        LOG.info("] return : {}", map);
        return map;
    }
    @GetMapping("/{id}/rating/{isPassenger}")
    public Double getRating(@PathVariable(name = "id") Long id, @PathVariable(name = "isPassenger") Boolean isPassenger) {
        LOG.info("[getUserRoutesGroupes : {} {}",id,  isPassenger);
        return usersService.getRating(id, isPassenger);
    }
}
