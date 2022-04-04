package com.example.demo.model;

import com.example.demo.model.enums.FoodCategory;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="item")
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique= true,nullable=false)
    private String name;

    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private double price;

    @Column(nullable=false)
    private FoodCategory category;

    /*
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="menu",
            joinColumns = @JoinColumn(name="item_id"),
            inverseJoinColumns = @JoinColumn(name="restaurant_id"))
    private Set<Restaurant> restaurants;
*/
    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="order_item",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="item_id"))
    private Set<Order> ordersSet;

    public FoodItem(){

    }

    public FoodItem(Integer id, String name, String description, double price, FoodCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public FoodItem(String name, String description, double price, FoodCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}
