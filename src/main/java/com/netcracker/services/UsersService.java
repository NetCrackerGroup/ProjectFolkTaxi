package com.netcracker.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netcracker.entities.User;
import com.netcracker.repositories.UserRepository;

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
    public Long createNewUser(String fio, String email, String phoneNumber){
        LOG.debug("[ createUser(fio : {}, email : {}, phoneNumber : {}", fio, email, phoneNumber);

        User user = new User(fio, email, phoneNumber);
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

        User user = usersRepository.find(email);

        LOG.debug("] (return : {})", user);
        return user;
    }
    
    /**
     * 
     * @return
     */
    /*public Iterable<User> getAllUsers(){
        LOG.debug("[ getAllUsers");

        Iterable<User> users = usersRepository.find();

        LOG.debug("] (return : {})", users);
        return users;
    }*/
	

}
