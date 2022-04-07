package com.example.demo.service;

import com.example.demo.model.FoodItem;
import com.example.demo.model.enums.Warning;
import com.example.demo.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    public Warning insertFoodItem(FoodItem foodItem){
        List<FoodItem> foodItemList=foodItemRepository.findAllByRestaurant(foodItem.getRestaurant());
        for(FoodItem item: foodItemList){
            if(item.getName().equals(foodItem.getName())){
                return Warning.DUPLICATE;
            }
        }
        foodItemRepository.save(foodItem);
        return Warning.SUCCESS;
    }
}
