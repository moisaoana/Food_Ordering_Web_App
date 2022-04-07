package com.example.demo.repository;

import com.example.demo.model.FoodItem;
import com.example.demo.model.Restaurant;
import com.example.demo.model.enums.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {

    List<FoodItem> findAllByRestaurant(Restaurant restaurant);
    List<FoodItem> findAllByRestaurantAndCategory(Restaurant restaurant, FoodCategory foodCategory);

}
