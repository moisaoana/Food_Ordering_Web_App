package com.example.demo.controller;


import com.example.demo.service.DeliveryZoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class StartPageController {
    @GetMapping("/start")
    public String index(){
        return "start";
    }

}
