package com.example.demo.service;

import com.example.demo.model.DeliveryZone;
import com.example.demo.repository.DeliveryZoneRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * This class defines the service methods for the DeliveryZone class.
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 25.04.2022
 */
@Service
public class DeliveryZoneService {
    private final static Logger LOGGER = Logger.getLogger(DeliveryZoneService.class.getName());
    /**
     * Access the DB through DeliveryZoneRepository
     */
    @Autowired
    private DeliveryZoneRepository deliveryZoneRepository;

    /**
     * Method for getting all the available zones from the DB
     * @return a list of DeliveryZone objects
     */
    public List<DeliveryZone> getAllZones(){
        LOGGER.info("Retrieving all zones from the DB");
        return deliveryZoneRepository.findAll();
    }
}
