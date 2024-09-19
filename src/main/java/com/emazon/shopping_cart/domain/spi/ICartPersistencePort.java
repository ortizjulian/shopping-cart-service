package com.emazon.shopping_cart.domain.spi;

import com.emazon.shopping_cart.domain.model.Cart;
import com.emazon.shopping_cart.domain.model.CartItem;

public interface ICartPersistencePort {
    boolean userHasCart(Long userId);
    void createCart(Long userId);
    Cart getCartByUserId(Long userId);
    CartItem getCartItem(Long cartId, Long userId);
    void updateCartItem(CartItem cartItem);
    void addCartItem(CartItem cartItem);
}
