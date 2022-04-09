package com.example.demo.controller;

import com.example.demo.dto.FoodItemDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @PostMapping("/order")
    public ResponseEntity getOrder(@RequestBody OrderDTO orderDTO){
        System.out.println(orderDTO.getRestaurant());
        System.out.println(orderDTO.getCustomer());
        for(FoodItemDTO f: orderDTO.getItems()){
            System.out.println(f.getName());
        }
        /*boolean result=userService.insertUser(userDTO);
        if(result) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO("User created"));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Username exists!"));
        }*/
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO("User created"));
    }
}
