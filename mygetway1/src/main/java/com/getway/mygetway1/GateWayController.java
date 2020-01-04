package com.getway.mygetway1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GateWayController {
    @RequestMapping("")
    public String showGateWay(){

        return "gateway";
    }
}
