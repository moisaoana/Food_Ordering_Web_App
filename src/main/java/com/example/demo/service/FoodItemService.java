package com.example.demo.service;

import com.example.demo.model.FoodItem;
import com.example.demo.model.enums.Warning;
import com.example.demo.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * This class defines the service methods for the FoodItem class.
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 25.04.2022
 */
@Service
public class FoodItemService {
    private final static Logger LOGGER = Logger.getLogger(FoodItemService.class.getName());
    /**
     * Access the DB through FoodItemRepository
     */
    @Autowired
    private FoodItemRepository foodItemRepository;

    /**
     * Method for inserting a new food item in the DB.
     * @param foodItem  a FoodItem object to be inserted in the DB
     * @return Warning.DUPLICATE (if a food item with the same name already exists)
     *         Warning.SUCCESS (if the insertion was successful)
     */
    public Warning insertFoodItem(FoodItem foodItem){
        LOGGER.info("Inserting "+foodItem.getName()+" food item in the DB");
        List<FoodItem> foodItemList=foodItemRepository.findAllByRestaurant(foodItem.getRestaurant());
        for(FoodItem item: foodItemList){
            if(item.getName().equals(foodItem.getName())){
                LOGGER.warning("A food item with the same name exists in the menu of restaurant "+foodItem.getRestaurant().getName());
                return Warning.DUPLICATE;
            }
        }
        foodItemRepository.save(foodItem);
        LOGGER.info("Insertion was successful");
        return Warning.SUCCESS;
    }

    /**
     * Method for finding all food items from the menu of a restaurant.
     * @param name a String representing the name of the restaurant
     * @return a list of FoodItem objects
     */
    public List<FoodItem> findAllByRestaurantName(String name){
        LOGGER.info("Retrieving all food items from the menu of "+ name);
        return foodItemRepository.findAllByRestaurant_Name(name);
    }
}
