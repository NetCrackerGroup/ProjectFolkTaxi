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
    @RequestMapping("/publica")
    public String publico() {
        return "Pagina Publica";
    }
    @RequestMapping("/privada")
    public String privada() {
        return "Pagina Privada";
    }
    @RequestMapping("/admin")
    public String admin() {
        return "Pagina Administrador";
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**", "/publica", "/helloUser").permitAll();
//			 .anyRequest().authenticated();
        http.requestMatchers().antMatchers("/privada", "/users/User" )
                .and().authorizeRequests()
                .antMatchers("/privada", "/users/User").access("hasRole('USER')")
                .and().requestMatchers().antMatchers("/admin", "/users/Admin")
                .and().authorizeRequests()
                .antMatchers("/admin", "/users/Admin").access("hasRole('ADMIN')");
    }

}
