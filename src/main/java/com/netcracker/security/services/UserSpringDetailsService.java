package com.netcracker.security.services;

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
@Service
public class UserSpringDetailsService implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserSpringDetailsService.class);

    @Autowired
    UsersService userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LOG.trace("[ (email : {})", s);

        com.netcracker.entities.User storedUser = userRepository.getUserByEmail(s);
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

        if (user == null) {
            LOG.error("User doesn't exist!");
            throw new UsernameNotFoundException("User doesn't exist!");
        }


        LOG.trace("] (user : {})", user);
        //это я вляется jwt юсером тоемть переделанным
        return user;
    }
}
