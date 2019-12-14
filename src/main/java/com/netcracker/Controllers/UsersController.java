package com.netcracker.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netcracker.Controllers.UsersController;
import com.netcracker.services.UsersService;
import com.netcracker.entities.User;

@RestController
@RequestMapping("user_1")
public class UsersController {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @PostMapping("")
    public Long createNewUser(@RequestParam  String fio, @RequestParam  String email, @RequestParam  String phoneNumber){
        LOG.debug("[ createUser(fio : {}, email : {}, phoneNumber : {}", fio, email, phoneNumber);

        Long userId = usersService.createNewUser(fio, email, phoneNumber);

        LOG.debug("] (userId : {})", userId);
        return userId;
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable(value="email") String email){
        LOG.info("[ getUserByEmail : {}", email);

        User user = usersService.getUserByEmail(email);

        LOG.info("] return : {}", user);
        return user;
    }

    /*@GetMapping("")
    public Iterable<User> getAllUsers(){
        LOG.info("[ getAllUsers");

        Iterable<User> users = usersService.getAllUsers();

        LOG.info("] (return : {})", users);
        return users;
    }*/

}
