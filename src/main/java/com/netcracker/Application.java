package com.netcracker;


import com.netcracker.Controllers.CityController;
import com.netcracker.Controllers.UsersController;
import com.netcracker.entities.City;
import com.netcracker.repositories.CityRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import com.netcracker.Controllers.UsersController;
//import com.netcracker.repositories.UserRepository;

@SpringBootApplication
public class Application {

    public static void main(String[] args){

        SpringApplication.run(Application.class, args);
//        cityCreateCity();

    }

//    private static void userCreateUser() {
//    	UsersController userCon = new UsersController();
//
//        City city = new City();
//    	userCon.createNewUser("Ilya", "ilya.aganin@gmail.com", "88005553535");
//
//    }
    private static void cityCreateCity() {
        CityController cityController = new CityController();
        cityController.createNewСity("New","City");
    }
}