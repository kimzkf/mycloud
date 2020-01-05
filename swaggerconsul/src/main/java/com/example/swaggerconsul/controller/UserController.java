package com.example.swaggerconsul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/swagger")
    public String testSwagger(String username,String password){
        System.out.println(username+"---"+password);
    return "testSwagger";
    }
}
