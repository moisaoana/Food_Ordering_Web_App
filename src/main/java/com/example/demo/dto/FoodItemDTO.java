package com.example.demo.dto;

import com.example.demo.model.enums.FoodCategory;
import com.sun.istack.NotNull;

import javax.persistence.Column;

public class FoodItemDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String price;

    @NotNull
    private String category;

    @NotNull
    private String admin_username;

    public FoodItemDTO(String name, String description, String price, String category, String admin_username) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.admin_username = admin_username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAdmin_username() {
        return admin_username;
    }

    public void setAdmin_username(String admin_username) {
        this.admin_username = admin_username;
    }
}
