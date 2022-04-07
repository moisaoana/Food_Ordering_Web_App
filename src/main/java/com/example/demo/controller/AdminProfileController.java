package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.ZoneDTO;
import com.example.demo.mapper.ZoneMapper;
import com.example.demo.model.DeliveryZone;
import com.example.demo.model.Restaurant;
import com.example.demo.model.enums.Warning;
import com.example.demo.service.DeliveryZoneService;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminProfileController {
    @Autowired
    private DeliveryZoneService deliveryZoneService;

    @Autowired
    private RestaurantService restaurantService;

    ZoneMapper zoneMapper=new ZoneMapper();

    @GetMapping("/adminprofile")
    public List<ZoneDTO> getZones(){
        List<DeliveryZone> deliveryZones=deliveryZoneService.getAllZones();
        List<ZoneDTO> dtos=new ArrayList<>();
        for(DeliveryZone z: deliveryZones){
            dtos.add(zoneMapper.convertToDTO(z));
        }
        return dtos;
    }
    @PostMapping("/adminprofile")
    public ResponseEntity createRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        System.out.println(restaurantDTO.getName()+" "+restaurantDTO.getLocation()+restaurantDTO.getAdminUsername());
        for(int i=0;i<restaurantDTO.getZones().size();i++){
            System.out.println(restaurantDTO.getZones().get(i));
        }
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
