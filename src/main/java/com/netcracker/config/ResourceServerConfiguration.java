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

        http.authorizeRequests()
                .antMatchers("/oauth/token", "/oauth/authorize**", "/helloUser", "/users/sign-up", "/users/", "/routes/**")
                .permitAll();
        http.authorizeRequests().anyRequest().fullyAuthenticated();

      /*  http
>>>>>>> NotificationMessage
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
      /*  http.authorizeRequests()
                .antMatchers("/oauth/token", "/oauth/authorize**", "/helloUser", "/users/sign-up", "/users/", "/group/notifications")
                .permitAll().and().authorizeRequests().anyRequest().fullyAuthenticated()
        ;*/
    }
}