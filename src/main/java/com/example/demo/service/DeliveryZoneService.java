package com.example.demo.service;

import com.example.demo.model.DeliveryZone;
import com.example.demo.repository.DeliveryZoneRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryZoneService {
    @Autowired
    private DeliveryZoneRepository deliveryZoneRepository;

    public List<DeliveryZone> getAllZones(){
        return deliveryZoneRepository.findAll();
    }
}
