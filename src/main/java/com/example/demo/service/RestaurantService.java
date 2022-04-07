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

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeliveryZoneRepository deliveryZoneRepository;

    public List<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }
    public boolean hasAdminRestaurantRegistered(String username){
        Optional<Restaurant> restaurant=restaurantRepository.findByAdmin_Username(username);
        return restaurant.isPresent();
    }

    public Warning insertRestaurant(RestaurantDTO restaurantDTO) {
        if (hasAdminRestaurantRegistered(restaurantDTO.getAdminUsername())) {
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

            return Warning.SUCCESS;
        }
    }

}
