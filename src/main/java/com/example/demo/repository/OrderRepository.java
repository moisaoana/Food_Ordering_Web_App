package com.example.demo.repository;

import com.example.demo.model.FoodItem;
import com.example.demo.model.Order;
import com.example.demo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByRestaurant_Name(String name);
    List<Order> findAllByCustomer_Username(String name);
    Optional<Order> findById(Integer id);
}
