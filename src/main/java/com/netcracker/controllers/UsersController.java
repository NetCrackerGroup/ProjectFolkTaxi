package com.netcracker.controllers;
import com.netcracker.DTO.UserAccDto;
import com.netcracker.DTO.UserDto;
import com.netcracker.DTO.UserSecDto;
import com.netcracker.exceptions.ResourceNotFoundException;
import com.netcracker.entities.City;
import com.netcracker.entities.Group;
import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;
import com.netcracker.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UsersController {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("/user/image")
    public String getUserImageForNav(){

        String image = usersService.getUserImageForNav();

        LOG.info("image : {}", image);

        return image;
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
        //System.out.println("`123123");
        UserDto user = usersService.getUserById(id);
        LOG.info("] return : {}", user);
        return user;
    }

    @GetMapping("/user/{id}")
    public UserAccDto getUserByIdForView(@PathVariable(name = "id") Long id) {
        LOG.info("[getUserByid : {}", id);
        UserAccDto user = usersService.getUserByIdForView(id);
        LOG.info("] return : {}", user);
        return user;
    }

    @GetMapping("/user/profile")
    public UserAccDto getUserForPro() {
        UserAccDto user = usersService.getUserForPro();
        LOG.info("] return : {}", user);
        return user;
    }

    @GetMapping("/{id}/city")
    public City getUserCity(@PathVariable(name = "id") Long id){
        LOG.info("[getUserCity : {}", id);
        City city = usersService.getUserCity(id);
        LOG.info("] return : {}", city);
        return city;
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

    @PostMapping("/update-user-fio")
    public ResponseEntity<User> updateUserFio(@RequestParam(value="fio") String fio){ return ResponseEntity.ok(usersService.updateUserFio(fio)); }

    @PostMapping("/update-user-city")
    public ResponseEntity<User> updateUserCity(@RequestParam(value="city") String city){ return ResponseEntity.ok(usersService.updateUserCity(city)); }

    @PostMapping("/update-user-phone-number")
    public ResponseEntity<User> updateUserPhoneNumber(@RequestParam(value="phoneNumber") String phoneNumber){ return ResponseEntity.ok(usersService.updateUserPhoneNumber(phoneNumber)); }

    @PostMapping("/update-user-info")
    public ResponseEntity<User> updateUserInfo(@RequestParam(value="info") String info){ return ResponseEntity.ok(usersService.updateUserInfo(info)); }

    @PostMapping("/update-user-image")
    public ResponseEntity<User> updateUserImage(@RequestParam(value="image") MultipartFile image) throws IOException { return ResponseEntity.ok(usersService.updateUserImage(image)); }

    @PostMapping("/update-user-email")
    public ResponseEntity<User> updateUserEmail(@RequestParam(value="email") String email,
                                                @RequestParam(value="currPassword") String currPassword){
        return ResponseEntity.ok(usersService.updateUserEmail(email, currPassword));

    }


    @PostMapping("/update-user-password")
    public ResponseEntity<User> updateUserPassword(@RequestParam(value="oldPassword") String oldPassword,
                                                   @RequestParam(value="currPassword") String currPassword){
        return ResponseEntity.ok(usersService.updateUserPassword(oldPassword, currPassword));

    }

    @PostMapping("/rate/driver-rating")
    public void updateUserDriverRating(@RequestParam(value="userId") Long userId,
                                       @RequestParam(value="driverRating") Double driverRating){
        usersService.rateDriver(userId, driverRating);

    }

}
