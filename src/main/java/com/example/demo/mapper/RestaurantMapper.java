package com.example.demo.mapper;

import com.example.demo.dto.RestaurantDTO;

import com.example.demo.model.DeliveryZone;
import com.example.demo.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMapper {
    public RestaurantDTO convertToDTO(Restaurant restaurant){
        List<String> zones=new ArrayList<>();
        for(DeliveryZone z: restaurant.getZones()){
            zones.add(z.getName());
        }
        return new RestaurantDTO(restaurant.getName(),restaurant.getLocation(),restaurant.getAdmin().getUsername(),zones);
    }
}
