package com.netcracker;

import com.netcracker.repositories.UserRepository;
import com.netcracker.security.services.UserDetService;
import com.netcracker.security.services.UserSpringDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class Application {

    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

    }
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    @Autowired
//    UserSpringDetailsService userSpringDetailsService;
//    @Autowired
//    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository userRepository) throws Exception {
//        builder.userDetailsService(userSpringDetailsService);
//    }
}