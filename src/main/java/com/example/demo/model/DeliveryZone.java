package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="zone")
public class DeliveryZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique= true,nullable=false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="restaurant_zones",
            joinColumns = @JoinColumn(name="zone_id"),
            inverseJoinColumns = @JoinColumn(name="restaurant_id"))
    private Set<Restaurant> restaurants;

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

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}

