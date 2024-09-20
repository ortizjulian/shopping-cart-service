package com.emazon.shopping_cart.domain.model;

public class AddArticle {
    private Long articleId;
    private Integer quantity;

    public AddArticle(Long articleId, Integer quantity) {
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public AddArticle() {

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
