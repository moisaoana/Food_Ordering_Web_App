package com.example.demo.controller;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.mapper.RestaurantMapper;
import com.example.demo.model.Restaurant;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserProfileController {
    @Autowired
    private RestaurantService restaurantService;

    RestaurantMapper restaurantMapper=new RestaurantMapper();

    @GetMapping("/userprofile")
    public List<RestaurantDTO> getRestaurants(){
        List<Restaurant> restaurants= restaurantService.getAllRestaurants();
        List<RestaurantDTO> dtos=new ArrayList<>();
        for(Restaurant r: restaurants){
            dtos.add(restaurantMapper.convertToDTO(r));
        }
        return  dtos;
    }

}
