package com.example.demo.controller;

import com.example.demo.dto.OrderWithStatusDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
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
public class ViewOrderHistoryController {

    private final static Logger LOGGER = Logger.getLogger(ViewOrderHistoryController.class.getName());

    @Autowired
    private OrderService orderService;

    OrderMapper orderMapper;

    public ViewOrderHistoryController(){
        orderMapper=new OrderMapper();
    }

    @GetMapping("/vieworderhistory/{name}")
    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderWithStatusDTO> getAllOrders(@PathVariable String name){
        LOGGER.info("GET method for displaying all orders of user "+name);
        List<Order> orders=orderService.getOrdersByCustomer(name);
        List<OrderWithStatusDTO> dtos=new ArrayList<>();
        for(Order o:orders){
            dtos.add(orderMapper.convertToDTO(o));
        }
        return dtos;
    }
}
