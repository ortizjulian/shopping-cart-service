package com.emazon.shopping_cart.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class Article {

    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Integer cartQuantity;
    private LocalDateTime nextRestockDate;
    private Float price;
    private Brand brand;
    private List<Category> categories;

    public Article() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public LocalDateTime getNextRestockDate() {
        return nextRestockDate;
    }

    public void setNextRestockDate(LocalDateTime nextRestockDate) {
        this.nextRestockDate = nextRestockDate;
    }
}
