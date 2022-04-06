package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {
    @GetMapping("/userprofile")
    public String index(){
        return "User profile";
    }

}
