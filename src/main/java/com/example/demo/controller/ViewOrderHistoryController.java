package com.example.demo.controller;

import com.example.demo.dto.OrderWithStatusDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ViewOrderHistoryController {
    @Autowired
    private OrderService orderService;

    OrderMapper orderMapper=new OrderMapper();

    @GetMapping("/vieworderhistory/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderWithStatusDTO> getAllOrders(@PathVariable String name){
        System.out.println("***********");
        List<Order> orders=orderService.getOrdersByCustomer(name);
        List<OrderWithStatusDTO> dtos=new ArrayList<>();
        for(Order o:orders){
            System.out.println(o.getId());
            dtos.add(orderMapper.convertToDTO(o));
        }
        return dtos;
    }
}
