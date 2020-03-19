package com.netcracker.controllers;
import com.netcracker.DTO.GroupDto;
import com.netcracker.DTO.UserDto;
import com.netcracker.DTO.UserSecDto;
import com.netcracker.entities.City;
import com.netcracker.entities.Group;
import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import com.netcracker.services.GroupMapper;
import com.netcracker.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.netcracker.services.UsersService;
import com.netcracker.entities.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UsersController {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UsersService usersService;

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods" , "GET, PUT, POST, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    }

    @Autowired
    @Lazy
    private PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("")
    public Long createNewUser(@RequestParam String fio,
                              @RequestParam String email,
                              @RequestParam String phoneNumber,
                              @RequestParam City city,
                              @RequestParam String password){
        LOG.debug("[ createUser(fio : {}, email : {}, phoneNumber : {}", fio, email, phoneNumber);
        Long userId = usersService.createNewUser(fio, email, phoneNumber, city, password, "ROLE_USER");

        LOG.debug("] (userId : {})", userId);
        return userId;
    }

    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable(value="email") String email){
        LOG.info("[ getUserByEmail : {}", email);

        UserDto user = usersService.getUserByEmail(email);

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
    public UserDto getUserByid(@PathVariable(name = "id") Long id) {
        LOG.info("[getUserByid : {}", id);
        System.out.println("`123123");
        UserDto user = usersService.getUserById(id);
        LOG.info("] return : {}", user);
        return user;
    }


    @GetMapping("/{id}/routes")
    public Collection<Route> getUserRoutes(@PathVariable(name = "id") Long id) {
        LOG.info("[getUserRoutes : {}", id);
        Collection<Route> routes = usersService.getUserRoute(id);
        LOG.info("] return : {}", routes);
        return routes;
    }

    @GetMapping("/groups")
    public Collection<GroupDto> getGroups() {

        Collection<Group> groups = usersService.getUserGroup();
        Collection<GroupDto> groupDtos = new LinkedList<GroupDto>();
        for (Group group : groups) {
            groupDtos.add(groupMapper.toDto(group));
        }
        return groupDtos;
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
    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserSecDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersService.saveNewUser(user);
    }
    @GetMapping("/helloUser")
    public ResponseEntity helloUser() {
        Map<Object, Object> response = new HashMap<>();
        response.put("hello", "hello user world");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/User")
    public ResponseEntity helloUserContr() {
        Map<Object, Object> response = new HashMap<>();
        response.put("hello", "hello User");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/Admin")
    public ResponseEntity helloAdmin() {
        Map<Object, Object> response = new HashMap<>();
        response.put("hello", "hello Admin");
        return  ResponseEntity.ok(response);
    }
}