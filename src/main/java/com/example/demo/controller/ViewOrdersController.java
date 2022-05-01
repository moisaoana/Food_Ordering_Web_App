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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class ViewOrdersController {

    private final static Logger LOGGER = Logger.getLogger(ViewOrdersController.class.getName());

    @Autowired
    private OrderService orderService;

    OrderMapper orderMapper;

    public ViewOrdersController() {
        orderMapper = new OrderMapper();
    }

    @GetMapping("/vieworders/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderWithStatusDTO> getAllOrders(@PathVariable String name){
        LOGGER.info("GET method for displaying all orders from restaurant "+name);
        List<Order> orders=orderService.getOrdersByRestaurant(name);
        List<OrderWithStatusDTO> dtos=new ArrayList<>();
        for(Order o:orders){
            dtos.add(orderMapper.convertToDTO(o));
        }
        return dtos;
    }

    @PutMapping("/vieworders")
    public ResponseEntity updateOrderStatus(@RequestBody OrderWithStatusDTO orderWithStatusDTO){
        LOGGER.info("PUT method for updating the status of the order");
        orderService.updateOrderStatus(orderWithStatusDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO("Order updated"));
    }
}
