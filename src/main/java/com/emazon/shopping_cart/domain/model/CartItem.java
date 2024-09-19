package com.emazon.shopping_cart.domain.model;

public class CartItem {
    private Long id;
    private Cart cart;
    private Long articleId;
    private Integer quantity;

    public CartItem(Long id, Cart cart, Long articleId, Integer quantity) {
        this.id = id;
        this.cart = cart;
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public CartItem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
