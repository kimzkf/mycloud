package com.example.eu_client.controller;

import com.example.bean.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

@Slf4j
@RestController
public class UserController {
    @GetMapping("/user/{id}")
    public User showUser(@PathVariable("id") String id){
        log.info(id+"8005");
        return new User(id,"李四","小牛8805",new Date());
    }
}
