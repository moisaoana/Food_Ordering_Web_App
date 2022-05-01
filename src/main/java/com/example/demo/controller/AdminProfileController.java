package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.ZoneDTO;
import com.example.demo.mapper.RestaurantMapper;
import com.example.demo.mapper.ZoneMapper;
import com.example.demo.model.DeliveryZone;
import com.example.demo.model.Restaurant;
import com.example.demo.model.User;
import com.example.demo.model.enums.UserType;
import com.example.demo.model.enums.Warning;
import com.example.demo.service.DeliveryZoneService;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class AdminProfileController {
    private final static Logger LOGGER = Logger.getLogger(AdminProfileController.class.getName());
    @Autowired
    private DeliveryZoneService deliveryZoneService;

    @Autowired
    private RestaurantService restaurantService;

    ZoneMapper zoneMapper;

    RestaurantMapper restaurantMapper;

    public AdminProfileController(){
        zoneMapper=new ZoneMapper();
        restaurantMapper=new RestaurantMapper();
    }

    @GetMapping("/adminprofile")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ZoneDTO> getZones(){
        LOGGER.info("GET method for displaying all available zones on Admin Profile Page");
        List<DeliveryZone> deliveryZones=deliveryZoneService.getAllZones();
        List<ZoneDTO> dtos=new ArrayList<>();
        for(DeliveryZone z: deliveryZones){
            dtos.add(zoneMapper.convertToDTO(z));
        }
        return dtos;
    }
    @GetMapping("/adminprofile/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public RestaurantDTO getRestaurant(@PathVariable String username){
        LOGGER.info("GET method for displaying registered restaurant on Admin Profile Page");
        Optional<Restaurant> restaurant= restaurantService.findRestaurantByAdmin(username);
        return restaurant.map(value -> restaurantMapper.convertToDTO(value)).orElse(null);
    }
    @PostMapping("/adminprofile")
    public ResponseEntity createRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        StringBuilder stringZones=new StringBuilder("Zones: ");
        for(int i=0;i<restaurantDTO.getZones().size();i++){
            stringZones.append(restaurantDTO.getZones().get(i)).append(", ");
        }
        LOGGER.info("POST method for inserting a new restaurant");
        LOGGER.info("Admin "+ restaurantDTO.getAdminUsername()+ " wants to register this restaurant: "+restaurantDTO.getName()+", "+restaurantDTO.getLocation()+", "+stringZones);
        Warning result=restaurantService.insertRestaurant(restaurantDTO);
        if(result==Warning.DUPLICATE){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO("Restaurant already registered"));
        }else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO("Restaurant created"));
        }
    }
}
