package com.example.demo.dto;

import com.example.demo.model.User;
import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.List;

public class RestaurantDTO {
    @NotNull
    private String name;

    @NotNull
    private String location;

    @NotNull
    private String adminUsername;

    @NotNull
    private List<String> zones;

    public RestaurantDTO(String name, String location, String adminUsername, List<String> zones) {
        this.name = name;
        this.location = location;
        this.adminUsername = adminUsername;
        this.zones = zones;
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

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public List<String> getZones() {
        return zones;
    }

    public void setZones(List<String> zones) {
        this.zones = zones;
    }
}
