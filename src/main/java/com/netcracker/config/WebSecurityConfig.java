package com.netcracker.config;

import com.netcracker.security.filters.JwtTokenBasicAuthenticationFilter;
import com.netcracker.security.filters.TokenAuthenticationFilter;
import com.netcracker.security.jwt.JwtTokenProvider;
import com.netcracker.security.services.UserSpringDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserSpringDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private static final String LOGIN_ENDPOINT = "/authenticate/login";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        //создание сессии не происходит
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()//все запросы должны быть авторизованны
                .antMatchers(LOGIN_ENDPOINT, "/users/sign-up", "/users").permitAll()
                .antMatchers("/helloAdmin").hasRole("ADMIN")
                .antMatchers("/users/helloUser").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated();

        //Implementing Token based authentication in this filter
        //добвалене созданных фильтров
        final TokenAuthenticationFilter tokenFilter = new TokenAuthenticationFilter(jwtTokenProvider, userDetailsService);
        http.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);

        //Creating token when basic authentication is successful and the same token can be used to authenticate for further requests
        //добвалене созданных фильтров
        final JwtTokenBasicAuthenticationFilter customBasicAuthFilter = new JwtTokenBasicAuthenticationFilter(jwtTokenProvider, this.authenticationManager());
        http.addFilter(customBasicAuthFilter);
    }
}


