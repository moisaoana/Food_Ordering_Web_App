package com.example.demo.model;

import com.example.demo.model.enums.FoodCategory;

import javax.persistence.*;
import java.util.List;

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


    @ManyToMany(mappedBy = "foodItems")
    private List<Order> ordersSet;

    public FoodItem(){

    }

    public FoodItem(Integer id, String name, String description, double price, FoodCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public FoodItem(String name, String description, double price, FoodCategory category,Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.restaurant=restaurant;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public FoodCategory getCategory() {
        return category;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Order> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(List<Order> ordersSet) {
        this.ordersSet = ordersSet;
    }
}
