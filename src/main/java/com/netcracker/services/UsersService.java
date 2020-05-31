package com.netcracker.services;


import com.netcracker.CustomException.NotFoundById;
import com.netcracker.CustomException.YandexAccountFailure;
import com.netcracker.DTO.PassengerForRateDto;
import com.netcracker.DTO.UserAccDto;
import com.netcracker.DTO.UserDto;
import com.netcracker.DTO.UserSecDto;
import com.netcracker.entities.*;
import com.netcracker.DTO.mappers.GroupMapper;
import com.netcracker.DTO.mappers.RouteMapper;
import com.netcracker.DTO.mappers.UserMapper;
import com.netcracker.entities.City;
import com.netcracker.entities.Group;
import com.netcracker.entities.Route;
import com.netcracker.entities.User;
import com.netcracker.repositories.*;

import com.netcracker.services.Channels.ChatSenderService;
import com.netcracker.services.Channels.EmailServiceImpl;
import com.netcracker.services.Channels.FillInfoContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class UsersService {
    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private RateHistoryRepository rateHistoryRepository;

    @Autowired
    private DriverRatingRepository driverRatingRepository;

    @Autowired
    private PassengerRatingRepository passengerRatingRepository;

    @Autowired
    private UserRepository usersRepository;
    private UserMapper userMapper;
    private UserAccMapper userAccMapper;

    @Autowired
    @Lazy
    private PasswordEncoder bCryptPasswordEncoder;
    private AuthUserComponent authUserComponent;
    private NotificationService notificationService;
    private EmailServiceImpl emailService;
    private InfoContentService infoContentService;

    @Autowired
    public void setInfoContentService(InfoContentService infoContentService) {
        this.infoContentService = infoContentService;
    }

    private RouteMapper routeMapper;
    private GroupMapper groupMapper;

    @Autowired
    public UsersService(    UserRepository usersRepository,
                            UserMapper userMapper,
                            AuthUserComponent authUserComponent,
                            NotificationService notificationService,
                            EmailServiceImpl emailService) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
        this.authUserComponent = authUserComponent;
        this.notificationService = notificationService;
        this.emailService = emailService;
    }

    @Autowired
    private CityRepository cityRepository;




    public Long createNewUser(String fio, String email, String phoneNumber, City city, String password, String securityRole) throws Exception {
        LOG.debug("[ createUser(fio : {}, email : {}, phoneNumber : {}", fio, email, phoneNumber);

        User user = new User(fio, email, phoneNumber, city, password, securityRole);
        DriverRating driverRating = new DriverRating(user.getUserId());

        Map<String, String> map = new HashMap<>();
        map.put("username", user.getFio());
        FillInfoContent fillInfoContent = new FillInfoContent(map);
        notificationService.notify(infoContentService.getInfoContentByKey("user_registered"), emailService, user, fillInfoContent);
        usersRepository.save(user);
        driverRatingRepository.save(driverRating);

        LOG.debug("] (userId : {})", user.getUserId());
        return user.getUserId();
    }

    public Long saveNewUser(UserSecDto userDto) throws Exception {
        LOG.debug("[ saveNewUser(fio : {}", userDto);
        Optional<City> city = cityRepository.findById((long) userDto.getCityId());
        User user = userDto.toUser(city.get());
        Map<String, String> map = new HashMap<>();
        user.setNumberOfComplaints((long) 0);
        user.setIsBan((long) 0);
        map.put("username", user.getFio());
        FillInfoContent fillInfoContent = new FillInfoContent(map);
        notificationService.notify(infoContentService.getInfoContentByKey("user_registered"), emailService, user, fillInfoContent);
        usersRepository.save(user);

        LOG.debug("] (userId : {})", user.getUserId());
        return user.getUserId();
    }

    public UserImage getUserImageForNav() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        return new UserImage("Image", user.getImage());
    }

    public User updateUserFio(String fio) {
        LOG.debug("[ updateUserData(fio : {}", fio);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        user.setFio(fio);
        LOG.debug("] (new fio : {})", user.getFio());

        final User updatedUser = usersRepository.save(user);
        return updatedUser;
    }

    public User updateUserCity(String city) {
        LOG.debug("[ updateUserData(city : {}", city);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        user.setCityId(cityRepository.findCityByCityName(city));
        LOG.debug("] (new city : {})", user.getCityId().getCityName());

        final User updatedUser = usersRepository.save(user);
        return updatedUser;
    }

    public User updateUserPhoneNumber(String phoneNumber) {
        LOG.debug("[ updateUserData(phone number : {}", phoneNumber);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        user.setPhoneNumber(phoneNumber);
        LOG.debug("] (new phoneNumber : {})", user.getPhoneNumber());

        final User updatedUser = usersRepository.save(user);
        return updatedUser;
    }

    public User updateUserInfo(String info) {
        LOG.debug("[ updateUserData(info : {}", info);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        // can't send null-value to back (probably crooked hands)
        if ( info.equals("") )
            user.setInfo(null);
        else
            user.setInfo(info);

        LOG.debug("] (new info : {})", user.getInfo());

        final User updatedUser = usersRepository.save(user);
        return updatedUser;
    }

    public User updateUserImage(MultipartFile image) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        // convert from wrapper Byte to its primitive type
        /*byte[] bytes = new byte[image.length];
        int i = 0;
        for (Byte b: image)
            bytes[i++] = b.byteValue();*/

        user.setImage( Base64.getEncoder().encodeToString(image.getBytes()) );

        LOG.debug("] (new image : {})", user.getImage());

        final User updatedUser = usersRepository.save(user);
        return updatedUser;
    }

   /* public User updateUserEmail(String email, String currPassword) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        LOG.info("info: {}", loggedInUser.toString());

        UserDetails userDetail = (UserDetails) loggedInUser
                .getPrincipal();

        if ( bCryptPasswordEncoder.matches( currPassword, usersRepository.findUserByEmail(userDetail.getUsername()).getPassword() ) ) {
            User user = userRepository.findUserByEmail(userDetail.getUsername());
            user.setEmail(email);
            final User updatedUser = usersRepository.save(user);
            return updatedUser;
        } else {
            LOG.debug("Current password : {} is not equal to password in database : {}", currPassword,
                      usersRepository.findUserByEmail(userDetail.getUsername()).getPassword());
            return null;
        }
    }*/

    public User updateUserPassword(String oldPassword, String currPassword) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        LOG.info("info: {}", loggedInUser.toString());

        UserDetails userDetail = (UserDetails) loggedInUser
                .getPrincipal();
        System.out.println(userDetail.getPassword());
        if ( bCryptPasswordEncoder.matches(oldPassword, usersRepository.findUserByEmail(userDetail.getUsername()).getPassword() ) ) {
            User user = userRepository.findUserByEmail(userDetail.getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(currPassword));
            final User updatedUser = usersRepository.save(user);
            return updatedUser;
        } else {
            LOG.debug("Old password : {} is not equal to password in database : {}", oldPassword,
                      usersRepository.findUserByEmail(userDetail.getUsername()).getPassword());
            return null;
        }
    }

    public Long rateDriver (Long driverId, Double rate, Long journeyId){

     Optional<User> posDriver = userRepository.findById(driverId);

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetail = (UserDetails) auth.getPrincipal();
    User rater = usersRepository.findUserByEmail(userDetail.getUsername());

     if ( posDriver.isPresent() && rater.getUserId() != driverId) {

         Optional<Journey> posJourney = journeyRepository.findById(journeyId);
         Journey journey = posJourney.get();
         User driver = posDriver.get();
         DriverRating driverRating = driverRatingRepository.findById(driver.getUserId()).get();
         Long numberOfVotes = driverRating.getNumberOfVotes();

         driverRating.setNumberOfVotes( numberOfVotes + 1 );
         driverRating.setAverageMark( ( driverRating.getAverageMark() * numberOfVotes  + rate ) / driverRating.getNumberOfVotes());
         driver.setDriverRating(driverRating.getAverageMark());
         RateHistory rateHistory = new RateHistory(journey, rater.getUserId(), driver, rate, true);

         rateHistoryRepository.save(rateHistory);
         driverRatingRepository.save(driverRating);
         userRepository.save(driver);

         return driver.getUserId();
     }
     else
         return null;
    }

    public Long ratePassenger (Long passengerId, Double rate, Long journeyId){

        Optional<User> posPassenger = userRepository.findById(passengerId);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User rater = usersRepository.findUserByEmail(userDetail.getUsername());

        if ( posPassenger.isPresent() && rater.getUserId() != passengerId) {

            Optional<Journey> posJourney = journeyRepository.findById(journeyId);
            Journey journey = posJourney.get();
            User passenger = posPassenger.get();
            PassengerRating passengerRating = passengerRatingRepository.findById(passenger.getUserId()).get();


            Long numberOfVotes = passengerRating.getNumberOfVotes();
            passengerRating.setNumberOfVotes( numberOfVotes + 1 );
            passengerRating.setAverageMark( ( passengerRating.getAverageMark() * numberOfVotes + rate ) / passengerRating.getNumberOfVotes());
            passenger.setPassengerRating(passengerRating.getAverageMark());
            RateHistory rateHistory = new RateHistory(journey, rater.getUserId(), passenger, rate, false);

            rateHistoryRepository.save(rateHistory);
            passengerRatingRepository.save(passengerRating);
            userRepository.save(passenger);

            return passenger.getUserId();
        }
        else
            return null;
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


    public City getUserCity(Long userId) {
        Optional<User> possible_user = usersRepository.findById(userId);
        return possible_user.isPresent() ? possible_user.get().getCityId() : null;
    }
    public User getUserByFIO(String fio) {
        LOG.debug("getUserByUsername(username: {})", fio);
        return usersRepository.findUserByFio(fio);
    }

    /*public Collection<Group> getUserGroup(Long userId) {
        Optional<User> possible_user = usersRepository.findById(userId);
        return possible_user.isPresent() ? possible_user.get().getGroups() : null;*/

    public Collection<Group> getUserGroup() {

        User user = authUserComponent.getUser();

        return user.getGroups();
    }

    public Long getUserId() {
        User user = authUserComponent.getUser();

        return user.getUserId();
    }

    public Collection<Group> getUserAsAdminGroup() {

        User user = authUserComponent.getUser();

        return user.getGroups();
    }

    public Collection<Route> getUserRoute() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = usersRepository.findUserByEmail(userDetail.getUsername());
        Collection<Route> routes = user.getRoutes();
        return routes;
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

    public Iterable<User> getUsersWithComplains(){
        LOG.debug("[getUsersWithComplains");
        Iterable<User> users = usersRepository.findUsers();
        LOG.debug("] (return : {})", users);
        return users;
    }
    public UserDto getUserById(Long userId) {
        System.out.println("`2223");
        Optional<User> user = usersRepository.findById(userId);
        return user.isPresent() ? userMapper.toDto(user.get()) : null;
    }

    public UserAccDto getUserByIdForView(Long userId) {
        LOG.debug("Get user by id {}", userId);
        Optional<User> user = usersRepository.findById(userId);
        LOG.debug("User - {}", user.get());
        return user.isPresent() ? (new UserAccMapper()).toUserAccDto(user.get()) : null;

    }

    public PassengerForRateDto getPassengerByIdForRate(Long userId) {
        LOG.debug("Get user by id {}", userId);
        Optional<User> user = usersRepository.findById(userId);
        LOG.debug("User - {}", user.get());
        return user.isPresent() ? (new PassengerForRateDtoMapper()).toPassengerForRateDtoMapper(user.get()) : null;

    }

    public UserAccDto getUserForPro() {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        LOG.info("info: {}", loggedInUser.toString());

        UserDetails userDetail = (UserDetails) loggedInUser
                .getPrincipal();

        User user = userRepository.findUserByEmail(userDetail.getUsername());
        return (new UserAccMapper()).toUserAccDto(user);
    }

    public boolean isAdmin() {
        boolean result = false;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = usersRepository.findUserByEmail(userDetail.getUsername());

        if(user.getSecurityRole().equals("ROLE_ADMIN"))
            result = true;

        LOG.debug("[ user( :{}",user.getSecurityRole() );
        return result;
    }

    public void complain(Long userId){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = usersRepository.findUserByEmail(userDetail.getUsername());

        User userComplain = usersRepository.findUserByUserId(userId);
        userComplain.setNumberOfComplaints(userComplain.getNumberOfComplaints() + 1);

        usersRepository.save(userComplain);


    }
    public void deleteUser(Long userId){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = usersRepository.findUserByEmail(userDetail.getUsername());

        if(!(user.getSecurityRole().equals("ROLE_ADMIN"))){
            throw new IllegalArgumentException("Can't delete user");}

        usersRepository.deleteById(userId);
    }
    public Long setBan(Long userId){

        Long result = Long.valueOf(0);
        if(this.isAdmin())
        {
            User user = usersRepository.findUserByUserId(userId);
            if(user.getIsBan()== 0)
                user.setIsBan(Long.valueOf(1));
            else user.setIsBan(Long.valueOf(0));

            if(user.isLocked())
                user.setLocked(false);
            else user.setLocked(true);
            usersRepository.save(user);
            result = user.getIsBan();

        }

        return  result;
    }

    public Long isBan(Long userId){

        User user = usersRepository.findUserByUserId(userId);
        return user.getIsBan();

    }
    public Long getUserLogged() {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        LOG.info("info: {}", loggedInUser.toString());

        UserDetails userDetail = (UserDetails) loggedInUser
                .getPrincipal();

        User user = userRepository.findUserByEmail(userDetail.getUsername());
        return user.getUserId();
    }

    public User addingYandexPurse(String account ) throws NumberFormatException, YandexAccountFailure {
        if (Objects.isNull(account))
            throw new YandexAccountFailure();
        Long yandexPurse = Long.valueOf(account);
        User user = authUserComponent.getUser();
        user.setYandexAccount(yandexPurse);
        usersRepository.save(user);
        return user;
    }

    public User getUser(Long id) throws NotFoundById {
        Optional<User>  optionalUser = usersRepository.findById(id);
        if ( !optionalUser.isPresent() ) {
            LOG.error("Not Found user by Id : {}", id);
            throw new NotFoundById();
        }
        return optionalUser.get();
    }
}