package com.emazon.shopping_cart.domain.api;

import com.emazon.shopping_cart.domain.model.AddArticle;

public interface ICartServicePort {
    void addToCart(AddArticle addArticle, Long userId);
}
