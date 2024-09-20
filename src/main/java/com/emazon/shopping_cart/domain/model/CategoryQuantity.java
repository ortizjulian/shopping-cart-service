package com.emazon.shopping_cart.domain.model;

public class CategoryQuantity {
    private String categoryName;
    private Long quantity;

    public CategoryQuantity(String categoryName, Long quantity) {
        this.categoryName = categoryName;
        this.quantity = quantity;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
