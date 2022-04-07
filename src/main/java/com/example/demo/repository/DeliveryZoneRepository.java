package com.example.demo.repository;

import com.example.demo.model.DeliveryZone;
import com.example.demo.model.FoodItem;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryZoneRepository extends JpaRepository<DeliveryZone,Integer> {

    Optional<DeliveryZone> findByName(String name);
}
