package com.netcracker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.netcracker.Controllers.UsersController;
//import com.netcracker.repositories.UserRepository;

@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
        //userCreateUser();
        
    }
    
    /*private static void userCreateUser() {
    	UsersController userCon = new UsersController();
    	System.out.println(userCon.createNewUser("Ilya", "ilya.aganin@gmail.com", "88005553535"));
    	
    }*/
}