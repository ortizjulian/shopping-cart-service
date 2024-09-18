package com.emazon.shopping_cart.application.handler;

import com.emazon.shopping_cart.application.dto.AddArticleRequest;

public interface ICartHandler {
    void addToCart(AddArticleRequest addArticleRequest, Long userId);
}
