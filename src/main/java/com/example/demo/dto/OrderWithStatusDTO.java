package com.example.demo.dto;

import com.example.demo.model.enums.Status;
import com.sun.istack.NotNull;

import java.util.List;

public class OrderWithStatusDTO {

    @NotNull
    private Integer id;

    @NotNull
    private String restaurant;

    @NotNull
    private String customer;

    @NotNull
    private Status status;

    @NotNull
    private List<FoodItemDTO> items;

    public OrderWithStatusDTO(Integer id,String restaurant, String customer, Status status, List<FoodItemDTO> items) {
        this.id=id;
        this.restaurant = restaurant;
        this.customer = customer;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<FoodItemDTO> getItems() {
        return items;
    }

    public void setItems(List<FoodItemDTO> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
