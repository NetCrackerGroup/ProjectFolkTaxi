package com.netcracker.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@EnableResourceServer
@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter
{

    @Override
    public void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**", "/helloUser", "/users/sign-up", "/users/")
                .permitAll();
                http.authorizeRequests().anyRequest().fullyAuthenticated();

    }

}