package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique= true,nullable=false)
    private String name;

    @Column(nullable=false)
    private String location;

    @OneToOne
    @JoinColumn(name="admin_id")
    private User admin;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="restaurant",fetch = FetchType.EAGER)
    private List<FoodItem> foodItems;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="restaurant")
    private List<Order> orders;

    @ManyToMany(mappedBy = "restaurants",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DeliveryZone> zones;

    public Restaurant(){

    }

    public Restaurant(Integer id, String name, String location, User admin) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.admin = admin;
    }

    public Restaurant(String name, String location, User admin) {
        this.name = name;
        this.location = location;
        this.admin = admin;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<DeliveryZone> getZones() {
        return zones;
    }

    public void setZones(List<DeliveryZone> zones) {
        this.zones = zones;
    }
}
