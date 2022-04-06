package com.example.demo.controller;

import com.example.demo.model.Restaurant;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserProfileController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/userprofile")
    public List<Restaurant> getRestaurants(){
        return restaurantService.getAllRestaurants();
    }

}
