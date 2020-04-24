package com.netcracker.controllers;
import com.netcracker.DTO.*;


import com.netcracker.DTO.mappers.GroupMapper;
import com.netcracker.DTO.mappers.UserModMapper;
import com.netcracker.entities.City;
import com.netcracker.entities.Group;
import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;

import com.netcracker.repositories.RouteRepository;

import com.netcracker.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UsersController {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserModMapper userModMapper;

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
                              @RequestParam String password) throws Exception {
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

    @GetMapping("/getAllUsers")
    public Iterable<UserModerDto> getAllUsers(){
        LOG.info("[ getAllUsers");

        Iterable<User> users = usersService.getAllUsers();
        Collection<UserModerDto> userDtos = new LinkedList<UserModerDto>();
        for (User user : users) {
            userDtos.add(userModMapper.toDto(user));
        }
        LOG.info("] (return : {})", userDtos);
        return userDtos;
    }


    @GetMapping("/getAllUsersWithComplains")
    public Iterable<UserModerDto> getAllUsersWithComplains(){
        LOG.info("[ getAllUsersWithComplains");

        Iterable<User> users = usersService.getUsersWithComplains();
        Collection<UserModerDto> userDtos = new LinkedList<UserModerDto>();
        for (User user : users) {
            userDtos.add(userModMapper.toDto(user));
        }
        LOG.info("] (return : {})", userDtos);

        return userDtos;
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

   /* @GetMapping("/{id}/groups")
    public Collection<Group> getUserGroup(@PathVariable(name = "id") Long id) {
        LOG.info("[getUserGroup : {}", id);
        Collection<Group> group = usersService.getUserGroup(id);
        LOG.info("] return : {}", group);
        return group;
    }*/

    @GetMapping("/routes")
    public Collection<Long> getUserRoutes() {
        LOG.info("[getUserRoutes : {}");
        Collection<Long> ids = new ArrayList<Long>() {};
        Collection<Route> routes = usersService.getUserRoute();
        for (Route route:
             routes) {

            Long id = route.getRouteId();
            ids.add(id);
        }
        LOG.info("] return : {}", routes);
        return ids;
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
    public void signUp(@RequestBody UserSecDto user) throws Exception {
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
    public ResponseEntity<User> updateUserImage(@RequestParam(value="file") MultipartFile file) throws IOException { return ResponseEntity.ok(usersService.updateUserImage(file)); }

    /*@PostMapping("/update-user-email")
    public ResponseEntity<User> updateUserEmail(@RequestParam(value="email") String email,
                                                @RequestParam(value="currPassword") String currPassword){
        return ResponseEntity.ok(usersService.updateUserEmail(email, currPassword));

    }*/


    @PostMapping("/update-user-password")
    public ResponseEntity<User> updateUserPassword(@RequestParam(value="oldPassword") String oldPassword,
                                                   @RequestParam(value="currPassword") String currPassword){
        return ResponseEntity.ok(usersService.updateUserPassword(oldPassword, currPassword));

    }

    @PostMapping("/rate/driver-rating")
    public void updateUserDriverRating(@RequestParam(value="userId") Long userId,
                                       @RequestParam(value="driverRating") Double driverRating,
                                       @RequestParam(value="journeyId") Long journeyId){
        usersService.rateDriver(userId, driverRating, journeyId);

    }

    @PostMapping("/rate/passenger-rating")
    public void updateUserPassengerRating(@RequestParam(value="userId") Long userId,
                                          @RequestParam(value="passengerRating") Double passengerRating,
                                          @RequestParam(value="journeyId") Long journeyId){
        usersService.ratePassenger(userId, passengerRating, journeyId);

    }
    @GetMapping("/isAdmin")
    public Map<String,Boolean> IsAdmin(){
        LOG.debug("#### checkUserIsAdmin #####");
        Boolean isAdmin =  usersService.isAdmin();


        Map<String, Boolean>  response = new HashMap<String, Boolean>() {{
            put("isAdmin", isAdmin);
        }};
        return response;
    }

    @PostMapping("/complain")
    public void complain(@RequestParam(value="userId") Long userId){
        usersService.complain(userId);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam(value="userId") Long userId){
        usersService.deleteUser(userId);
    }


    @PutMapping("/ban")
    public Map<String,Long> setBan(@RequestParam(value="userId") Long userId){

        Long result = usersService.setBan(userId);
        Map<String, Long>  response = new HashMap<String, Long>() {{
            put("isBan", result);
        }};

        return response;

    }

    @GetMapping("/isban")
    public Map<String, Long> isBan(@RequestParam(value="userId") Long userId){

        Long result = usersService.isBan(userId);
        Map<String, Long>  response = new HashMap<String, Long>() {{
            put("isBan", result);
        }};

        return response;
    }

    @GetMapping("/getLogged")
    public Map<String, Long> getUserLogged(){
        Long result = usersService.getUserLogged();
        Map<String, Long>  response = new HashMap<String, Long>() {{
            put("isLogged", result);
        }};
        return response;

    }
}

