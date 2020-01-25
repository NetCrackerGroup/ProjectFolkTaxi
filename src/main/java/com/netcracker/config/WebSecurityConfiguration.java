package com.netcracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {



    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http

                .authorizeRequests()
                .antMatchers("/index","/webpublico", "/publica").permitAll()
                .antMatchers("/webprivado").authenticated()
                    .antMatchers("/users/helloUser").hasRole("USER")
                .antMatchers("/webadmin", "/privada").hasRole("ADMIN").and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout() // Metodo get pues he desabilitado CSRF
                .permitAll();
    }


}
