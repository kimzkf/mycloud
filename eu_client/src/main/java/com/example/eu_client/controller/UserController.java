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

@RestController
@Slf4j
public class UserController {
    @Autowired
    private EurekaClient euClient;

    @GetMapping("/info")
    public String info(){
        InstanceInfo instanceInfo=euClient.getNextServerFromEureka("EU_CLIENT",false);
        return  instanceInfo.getHomePageUrl();
    }
}
