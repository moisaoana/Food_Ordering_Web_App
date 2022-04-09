package com.example.demo.dto;

import com.sun.istack.NotNull;

import java.util.List;

public class OrderDTO {
    @NotNull
    private String restaurant;

    @NotNull
    private String customer;

    @NotNull
    private List<FoodItemDTO> items;

    public OrderDTO(String restaurant, String customer, List<FoodItemDTO> items) {
        this.restaurant = restaurant;
        this.customer = customer;
        this.items = items;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<FoodItemDTO> getItems() {
        return items;
    }

    public void setItems(List<FoodItemDTO> items) {
        this.items = items;
    }
}
