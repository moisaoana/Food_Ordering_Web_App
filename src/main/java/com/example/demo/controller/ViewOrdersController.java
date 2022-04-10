package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.FoodItem;
import com.example.demo.model.Order;
import com.example.demo.service.FoodItemService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ViewOrdersController {
    @Autowired
    private OrderService orderService;

    OrderMapper orderMapper=new OrderMapper();

    @GetMapping("/vieworders/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderWithStatusDTO> getAllOrders(@PathVariable String name){
        List<Order> orders=orderService.getOrdersByRestaurant(name);
        List<OrderWithStatusDTO> dtos=new ArrayList<>();
        for(Order o:orders){
            dtos.add(orderMapper.convertToDTO(o));
        }
        return dtos;
    }

    @PutMapping("/vieworders")
    public ResponseEntity updateOrderStatus(@RequestBody OrderWithStatusDTO orderWithStatusDTO){
        System.out.println(orderWithStatusDTO.getStatus());
        orderService.updateOrderStatus(orderWithStatusDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO("Order updated"));

    }
}
