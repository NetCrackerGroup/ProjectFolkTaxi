package com.netcracker.security.services;

import com.netcracker.repositories.UserRepository;
import com.netcracker.security.SecurityRole;
import com.netcracker.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service("userDetailsService")
public class UserSpringDetailsService implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserSpringDetailsService.class);

    @Autowired
    UsersService userRepository;
    @Autowired
    UserRepository userRepository2;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LOG.trace("[ (email : {})", s);

        com.netcracker.entities.User storedUser = userRepository2.findUserByEmail(s);
        SecurityRole SR = SecurityRole.ROLE_USER;
        if(!storedUser.getSecurityRole().equals("ROLE_USER")) {
            SR = SecurityRole.ROLE_ADMIN;
        }
        //сюда нужно добавить все поля нашего класса
        User user = new User(
                storedUser.getEmail(),
                storedUser.getPassword(),
                Collections.singleton(SR)
        );


        LOG.trace("] (user : {})", user);
        //это я вляется jwt юсером тоемть переделанным
        return user;
    }
}
