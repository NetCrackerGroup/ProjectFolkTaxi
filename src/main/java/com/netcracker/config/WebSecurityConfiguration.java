package com.netcracker.config;

import com.netcracker.security.services.UserSpringDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
    @Autowired
    private UserSpringDetailsService userSpringDetailsService ;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userSpringDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/helloUser").permitAll()
                .antMatchers("/webprivado").authenticated()
                .antMatchers("/webprivado", "/users/User").hasRole("USER")
                .antMatchers("/webadmin", "/users/Admin").hasRole("ADMIN")
//                .and().rememberMe();
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout() // Metodo get pues he desabilitado CSRF
                .permitAll();
    }



}
