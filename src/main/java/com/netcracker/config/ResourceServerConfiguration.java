package com.netcracker.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
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
<<<<<<< HEAD
        http.authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**", "/helloUser", "/users/sign-up", "/users/")
=======
/*
        http.authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**", "/helloUser", "/users/sign-up", "/users/", "/routes/**")
>>>>>>> 7c80d1ea3d4a87a876ef15e2d5864381a6a18212
                .permitAll();
        http.authorizeRequests().anyRequest().fullyAuthenticated();

<<<<<<< HEAD
=======
    }
    */

        http
                .authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**", "/helloUser", "/users/sign-up", "/routes/**").permitAll();
//			 .anyRequest().authenticated();
        http.requestMatchers().antMatchers( "/users/User", "/routes/add" )
                .and().authorizeRequests()
                .antMatchers( "/users/User", "/routes/add").access("hasAnyRole('USER', 'ADMIN')")
                .and().requestMatchers().antMatchers( "/users/Admin")
                .and().authorizeRequests()
                .antMatchers("/users/Admin").access("hasRole('ADMIN')");
   }

>>>>>>> 7c80d1ea3d4a87a876ef15e2d5864381a6a18212

        /*http
                .authorizeRequests()
                .antMatchers("/oauth/token", "/oauth/authorize**", "/helloUser",  "/users/sign-up")
                .permitAll();
//			 .anyRequest().authenticated();
        http.requestMatchers()
                .antMatchers( "/users/User", "/group",  "/group/", "/group/entergroup/*", "/group/useringroup/")
                .and().authorizeRequests()
                .antMatchers( "/users/User", "/group", "/group/", "/group/entergroup/*", "/group/useringroup/" )
                .access(
                        "hasAnyRole('USER', 'ADMIN')")
                .and().requestMatchers().antMatchers( "/users/Admin")
                .and().authorizeRequests()
                .antMatchers("/users/Admin").access("hasRole('ADMIN')");*/
        /*http.authorizeRequests()
                .antMatchers("/oauth/token", "/oauth/authorize**", "/helloUser", "/users/sign-up", "/users/", "/group/notifications")
                .permitAll().and().authorizeRequests().anyRequest().fullyAuthenticated()
        ;*/
    }
}