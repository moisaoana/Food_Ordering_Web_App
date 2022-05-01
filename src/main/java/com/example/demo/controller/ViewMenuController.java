package com.example.demo.controller;

import com.example.demo.dto.FoodItemDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.mapper.FoodItemMapper;
import com.example.demo.model.FoodItem;
import com.example.demo.model.Restaurant;
import com.example.demo.service.FoodItemService;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class ViewMenuController {

    private final static Logger LOGGER = Logger.getLogger(ViewMenuController.class.getName());

    @Autowired
    private FoodItemService foodItemService;

    FoodItemMapper foodItemMapper;

    public ViewMenuController(){
        foodItemMapper=new FoodItemMapper();
    }

    @GetMapping("/viewmenu/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodItemDTO> getAllItemsFromMenu(@PathVariable String name){
        LOGGER.info("GET method for displaying all food items from the  menu of restaurant "+name);
        List<FoodItem> foodItemList=foodItemService.findAllByRestaurantName(name);
        List<FoodItemDTO> dtos=new ArrayList<>();
        for(FoodItem f:foodItemList){
            dtos.add(foodItemMapper.convertToDTO(f));
        }
        return dtos;
    }
}
