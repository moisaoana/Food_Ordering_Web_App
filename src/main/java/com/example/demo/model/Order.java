package com.example.demo.model;

import com.example.demo.model.enums.Status;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private Status orderStatus;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private User customer;

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "ordersSet",fetch = FetchType.EAGER)
    private Set<FoodItem> foodItems;

    public Order(){

    }

    public Order(Integer id, Status orderStatus, User customer, Restaurant restaurant) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.restaurant = restaurant;
    }

    public Order(Status orderStatus, User customer, Restaurant restaurant) {
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status status) {
        this.orderStatus = status;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(Set<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}
