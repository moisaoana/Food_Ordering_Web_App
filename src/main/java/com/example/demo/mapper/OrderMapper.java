package com.example.demo.mapper;

import com.example.demo.dto.FoodItemDTO;
import com.example.demo.dto.OrderWithStatusDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.model.DeliveryZone;
import com.example.demo.model.FoodItem;
import com.example.demo.model.Order;
import com.example.demo.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    FoodItemMapper foodItemMapper=new FoodItemMapper();
    public OrderWithStatusDTO convertToDTO(Order order){
        List<FoodItemDTO> foodItemDTOS=new ArrayList<>();
        for(FoodItem foodItem: order.getFoodItems()) {
            foodItemDTOS.add(foodItemMapper.convertToDTO(foodItem));
        }
        return new OrderWithStatusDTO(order.getId(), order.getRestaurant().getName(),order.getCustomer().getUsername(),order.getOrderStatus(),foodItemDTOS);
    }
}
