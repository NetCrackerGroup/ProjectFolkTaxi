package com.netcracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
@Order(-1)
@Configuration
@EnableWebSecurity
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
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
        http
                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
                .antMatchers( "/oauth/authorize**", "/publica", "/users/sign-up").permitAll();
//			 .anyRequest().authenticated();
        http.requestMatchers().antMatchers("/oauth/token","/privada")
                .and().authorizeRequests()
                .antMatchers("/users/helloUser").access("hasRole('USER')")
                .and().requestMatchers().antMatchers("/admin")
                .and().authorizeRequests()
                .antMatchers("/admin").access("hasRole('ADMIN')");
    }




}