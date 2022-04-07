package com.example.demo.mapper;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.ZoneDTO;
import com.example.demo.model.DeliveryZone;
import com.example.demo.model.User;

public class ZoneMapper {
    public ZoneDTO convertToDTO(DeliveryZone zone){
        return new ZoneDTO(zone.getName());
    }
}
