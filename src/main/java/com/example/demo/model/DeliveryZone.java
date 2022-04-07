package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="zone")
public class DeliveryZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique= true,nullable=false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="restaurant_zones",
            joinColumns = @JoinColumn(name="zone_id"),
            inverseJoinColumns = @JoinColumn(name="restaurant_id"))
    private List<Restaurant> restaurants;

    public DeliveryZone(){

    }

    public DeliveryZone(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public DeliveryZone(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}

