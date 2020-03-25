package com.netcracker.services;

import com.netcracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netcracker.entities.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class AuthUserComponent {

    @Autowired
    private UserRepository userRepository;

    Logger LOG = LoggerFactory.getLogger(AuthUserComponent.class);

    public AuthUserComponent() {
    }

    public User getUser () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByEmail(auth.getName());
        LOG.debug("{}", auth.getPrincipal());
        LOG.debug("{}", auth.getDetails());
        LOG.debug("{}", user);
        return user;
    }

}
