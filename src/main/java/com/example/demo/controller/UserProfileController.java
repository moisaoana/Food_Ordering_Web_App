package com.example.demo.controller;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.mapper.RestaurantMapper;
import com.example.demo.model.Restaurant;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserProfileController {

    private final static Logger LOGGER = Logger.getLogger(UserProfileController.class.getName());

    @Autowired
    private RestaurantService restaurantService;

    RestaurantMapper restaurantMapper;

    public UserProfileController(){
        restaurantMapper=new RestaurantMapper();
    }

    @GetMapping("/userprofile")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<RestaurantDTO> getRestaurants(){
        LOGGER.info("GET method for displaying all available restaurant");
        List<Restaurant> restaurants= restaurantService.getAllRestaurants();
        List<RestaurantDTO> dtos=new ArrayList<>();
        for(Restaurant r: restaurants){
            dtos.add(restaurantMapper.convertToDTO(r));
        }
        return  dtos;
    }

}
