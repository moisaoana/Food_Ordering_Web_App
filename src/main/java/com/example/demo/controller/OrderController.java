package com.example.demo.controller;

import com.example.demo.dto.FoodItemDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.FoodItemMapper;
import com.example.demo.model.FoodItem;
import com.example.demo.service.FoodItemService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class OrderController {
    private final static Logger LOGGER = Logger.getLogger(OrderController.class.getName());
    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodItemService foodItemService;

    FoodItemMapper foodItemMapper;

    public OrderController(){
        foodItemMapper=new FoodItemMapper();
    }


    @GetMapping("/order/{name}")
    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public List<FoodItemDTO> getAllItemsFromMenu(@PathVariable String name){
        LOGGER.info("GET method for displaying the menu of restaurant "+name);
        List<FoodItem> foodItemList=foodItemService.findAllByRestaurantName(name);
        List<FoodItemDTO> dtos=new ArrayList<>();
        for(FoodItem f:foodItemList){
            dtos.add(foodItemMapper.convertToDTO(f));
        }
        return dtos;
    }

    @PostMapping("/order")
    public ResponseEntity getOrder(@RequestBody OrderDTO orderDTO){
        LOGGER.info("POST method for placing order by "+orderDTO.getCustomer()+" at restaurant "+orderDTO.getRestaurant());
        orderService.addOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO("Order created"));
    }
}
