package com.emazon.shopping_cart.application.dto;

import com.emazon.shopping_cart.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AddArticleRequest {
    @NotNull(message = Constants.EXCEPTION_ARTICLE_ID_NULL)
    private Long articleId;

    @NotNull(message = Constants.EXCEPTION_ARTICLE_QUANTITY_NULL)
    @Positive(message = Constants.EXCEPTION_ARTICLE_QUANTITY_POSITIVE)
    private Integer quantity;

    public AddArticleRequest(Long articleId, Integer quantity) {
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public @NotNull(message = Constants.EXCEPTION_ARTICLE_ID_NULL) Long getArticleId() {
        return articleId;
    }

    public void setArticleId(@NotNull(message = Constants.EXCEPTION_ARTICLE_ID_NULL) Long articleId) {
        this.articleId = articleId;
    }

    public @NotNull(message = Constants.EXCEPTION_ARTICLE_QUANTITY_NULL) @Positive(message = Constants.EXCEPTION_ARTICLE_QUANTITY_POSITIVE) Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull(message = Constants.EXCEPTION_ARTICLE_QUANTITY_NULL) @Positive(message = Constants.EXCEPTION_ARTICLE_QUANTITY_POSITIVE) Integer quantity) {
        this.quantity = quantity;
    }
}
