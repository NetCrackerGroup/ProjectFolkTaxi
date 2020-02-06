package com.netcracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


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