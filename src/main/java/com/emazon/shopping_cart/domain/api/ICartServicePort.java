package com.emazon.shopping_cart.domain.api;

import com.emazon.shopping_cart.domain.model.AddArticle;
import com.emazon.shopping_cart.domain.model.CartItems;
import com.emazon.shopping_cart.domain.model.PageCustom;

public interface ICartServicePort {
    void addToCart(AddArticle addArticle, Long userId);
    void deleteItem(Long articleId, Long userId);
    CartItems getAllItems(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName, Long userId);
}
