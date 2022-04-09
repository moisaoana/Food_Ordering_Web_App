package com.example.demo.service;


import com.example.demo.dto.FoodItemDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderWithStatusDTO;
import com.example.demo.model.FoodItem;
import com.example.demo.model.Order;
import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import com.example.demo.model.enums.Status;
import com.example.demo.repository.FoodItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    public void addOrder(OrderDTO orderDTO){
        Optional<Restaurant> restaurant=restaurantRepository.findByName(orderDTO.getRestaurant());
        Optional<User> customer=userRepository.findByUsername(orderDTO.getCustomer());
        if(restaurant.isPresent() && customer.isPresent()) {
            Order newOrder = new Order(Status.PENDING, customer.get(), restaurant.get());
            List<FoodItem> foodItemList=new ArrayList<>();
            for(FoodItemDTO foodItemDTO: orderDTO.getItems()){
                //FoodItem foodItem=foodItemMapper.convertFromDTO(foodItemDTO, restaurant.get());
                FoodItem foodItem=foodItemRepository.findByNameAndRestaurant(foodItemDTO.getName(),restaurant.get()).get(0);
                foodItemList.add(foodItem);
            }
            newOrder.setFoodItems(foodItemList);
            for(FoodItem f: foodItemList){
                f.getOrdersSet().add(newOrder);
            }
            restaurant.get().getOrders().add(newOrder);
            customer.get().getOrders().add(newOrder);
            orderRepository.save(newOrder);
        }
    }
    public List<Order> getOrdersByRestaurant(String name){
        return orderRepository.findAllByRestaurant_Name(name);
    }
    public List<Order> getOrdersByCustomer(String name){
        return orderRepository.findAllByCustomer_Username(name);
    }

    public void updateOrderStatus(OrderWithStatusDTO orderWithStatusDTO){
        Optional<Order> order=orderRepository.findById(orderWithStatusDTO.getId());
        if(order.isPresent()){
            order.get().setOrderStatus(orderWithStatusDTO.getStatus());
            orderRepository.save(order.get());
        }
    }
}
