package com.netcracker.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class home {

    @RequestMapping("/")
    String home(){
        return "hello!";
    }

}
