package com.emazon.shopping_cart.application.handler;

import com.emazon.shopping_cart.application.dto.AddArticleRequest;
import com.emazon.shopping_cart.domain.model.CartItems;

public interface ICartHandler {
    void addToCart(AddArticleRequest addArticleRequest, Long userId);
    void deleteItem(Long articleId, Long userId);
    CartItems getAllItems(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName, Long userId);
}
