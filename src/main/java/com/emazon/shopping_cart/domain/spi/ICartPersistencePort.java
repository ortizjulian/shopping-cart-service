package com.emazon.shopping_cart.domain.spi;

import com.emazon.shopping_cart.domain.model.Cart;
import com.emazon.shopping_cart.domain.model.CartItem;

import java.util.List;
import java.util.Optional;


public interface ICartPersistencePort {
    Cart createCart(Long userId);
    Optional<Cart> getCartByUserId(Long userId);
    CartItem getCartItem(Long cartId, Long articleId);
    void saveCartItem(CartItem cartItem);
    List<Long> getArticleIdsFromCart(Long cartId);
    void updateCart(Cart cart);
    void deleteItem(Long articleId, Long userId);
}
