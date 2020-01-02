package com.example.eu_server.controller;

import com.example.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 主程序--生产者
 */
@Slf4j
@RestController
public class UserController {
    @GetMapping("/user/{id}")
    public User showUser(@PathVariable("id") String id){
        log.info(id);
        return new User(id,"李四","小牛",new Date());
    }
}
