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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class AddMenuItemController {
    private final static Logger LOGGER = Logger.getLogger(AddMenuItemController.class.getName());
    FoodItemMapper foodItemMapper=new FoodItemMapper();

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/addmenuitem")
    @PreAuthorize("hasRole('ADMIN')")
    public String start(){
        LOGGER.info("Entered the Add Menu Item Page as ADMIN");
        return "start";
    }

    @PostMapping("/addmenuitem")
    public ResponseEntity createMenuItem(@RequestBody FoodItemDTO foodItemDTO){
        LOGGER.info("Food item entered by the admin: "+foodItemDTO.getName()+", "+foodItemDTO.getDescription()+", "+foodItemDTO.getPrice()+", "+foodItemDTO.getCategory());
        Optional<Restaurant> restaurant=restaurantService.findRestaurantByAdmin(foodItemDTO.getAdmin_username());
        FoodItem foodItem=foodItemMapper.convertFromDTO(foodItemDTO,restaurant.get());
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
