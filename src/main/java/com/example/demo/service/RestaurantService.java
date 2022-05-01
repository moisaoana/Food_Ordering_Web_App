package com.example.demo.service;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.model.DeliveryZone;
import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import com.example.demo.model.enums.Warning;
import com.example.demo.repository.DeliveryZoneRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * This class defines the service methods for the Restaurant class.
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 25.04.2022
 */
@Service
public class RestaurantService {
    private final static Logger LOGGER = Logger.getLogger(RestaurantService.class.getName());

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeliveryZoneRepository deliveryZoneRepository;

    /**
     * Method for retrieving all restaurants from the DB
     * @return list of Restaurant objects
     */
    public List<Restaurant> getAllRestaurants() {
        LOGGER.info("Retrieving all restaurants from the DB");
        return restaurantRepository.findAll();
    }

    /**
     * Method for checking if an administrator has registered a restaurant
     * @param username a String representing the username of the administrator
     * @return true (if a restaurant is registered) or false (otherwise)
     */
    public boolean hasAdminRestaurantRegistered(String username) {
        LOGGER.info("Checking if "+username+" has a restaurant registered");
        Optional<Restaurant> restaurant = restaurantRepository.findByAdmin_Username(username);
        return restaurant.isPresent();
    }

    /**
     * Method for finding a restaurant based on the username of its administrator.
     * @param username a String representing the username of the administrator
     * @return it optionally returns a Restaurant object
     */
    public Optional<Restaurant> findRestaurantByAdmin(String username){
       return restaurantRepository.findByAdmin_Username(username);
    }

    //public Optional<Restaurant> findRestaurantByAdmin(User admin){
    //   return restaurantRepository.findByAdmin(admin);
    //}

    /**
     * Method for inserting a new restaurant in the DB.
     * @param restaurantDTO a DTO containing all information required for inserting a restaurant in the DB
     * @return Warning.DUPLICATE (if the admin has already registered a restaurant)
     *         Warning.SUCCESS (if the insertion was successful)
     */
    public Warning insertRestaurant(RestaurantDTO restaurantDTO) {
        LOGGER.info("Inserting a new restaurant in the DB");
        if (hasAdminRestaurantRegistered(restaurantDTO.getAdminUsername())) {
            LOGGER.warning("The admin already has a restaurant registered");
            return Warning.DUPLICATE;
        } else {
            Optional<User> admin = userRepository.findByUsername(restaurantDTO.getAdminUsername());
            List<DeliveryZone> zones = new ArrayList<>();
            for (int i = 0; i < restaurantDTO.getZones().size(); i++) {
                Optional<DeliveryZone> zone = deliveryZoneRepository.findByName(restaurantDTO.getZones().get(i));
                zone.ifPresent(zones::add);
            }
            if (admin.isPresent()) {
                Restaurant restaurant = new Restaurant(restaurantDTO.getName(), restaurantDTO.getLocation(), admin.get());
                restaurant.setZones(zones);

                for (DeliveryZone z : zones) {
                    z.getRestaurants().add(restaurant);
                }
                restaurantRepository.save(restaurant);
            }
            LOGGER.info("Insertion of restaurant "+restaurantDTO.getName()+" was successful");
            return Warning.SUCCESS;
        }
    }
}
