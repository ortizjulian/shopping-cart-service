package com.emazon.shopping_cart.domain.model;

import java.time.LocalDateTime;

public class Cart {
    private Long articleId;
    private Long userId;
    private Integer quantity;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Cart(Long articleId, Long userId, Integer quantity, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.articleId = articleId;
        this.userId = userId;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
