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
}
