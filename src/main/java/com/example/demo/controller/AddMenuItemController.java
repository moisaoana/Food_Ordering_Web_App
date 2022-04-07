package com.example.demo.controller;

import com.example.demo.dto.FoodItemDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.mapper.FoodItemMapper;
import com.example.demo.model.FoodItem;
import com.example.demo.model.Restaurant;
import com.example.demo.model.enums.Warning;
import com.example.demo.service.FoodItemService;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AddMenuItemController {
    FoodItemMapper foodItemMapper=new FoodItemMapper();

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/addmenuitem")
    public ResponseEntity createMenuItem(@RequestBody FoodItemDTO foodItemDTO){
        System.out.println(foodItemDTO.getName()+" "+foodItemDTO.getDescription()+" "+foodItemDTO.getPrice()+" "+foodItemDTO.getCategory()+" "+foodItemDTO.getAdmin_username());
        Optional<Restaurant> restaurant=restaurantService.findRestaurantByAdmin(foodItemDTO.getAdmin_username());
        FoodItem foodItem=foodItemMapper.convertFromDTO(foodItemDTO,restaurant.get());
        System.out.println(foodItem.getName());
        System.out.println(foodItem.getDescription());
        System.out.println(foodItem.getPrice());
        System.out.println(foodItem.getCategory());
        System.out.println(foodItem.getRestaurant().getName());
        Warning result=foodItemService.insertFoodItem(foodItem);
        if(result==Warning.SUCCESS) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO("Food item created"));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("Food item already created"));
        }
    }
}
