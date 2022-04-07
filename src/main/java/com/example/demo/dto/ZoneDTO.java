package com.example.demo.dto;

import com.sun.istack.NotNull;

public class ZoneDTO {
    @NotNull
    private String name;

    public ZoneDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
