package com.emazon.shopping_cart.domain.usecase;

import com.emazon.shopping_cart.domain.api.ICartServicePort;
import com.emazon.shopping_cart.domain.model.AddArticle;
import com.emazon.shopping_cart.domain.model.Cart;
import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.domain.spi.ICartPersistencePort;

public class CartUseCase implements ICartServicePort {

    private final ICartPersistencePort cartPersistencePort;

    public CartUseCase(ICartPersistencePort cartPersistencePort) {
        this.cartPersistencePort = cartPersistencePort;
    }

    @Override
    public void addToCart(AddArticle addArticle,Long userId) {
        if(!cartPersistencePort.userHasCart(userId)){
            cartPersistencePort.createCart(userId);
        }

        Cart cart = cartPersistencePort.getCartByUserId(userId);

        CartItem cartItem = cartPersistencePort.getCartItem(cart.getId(), addArticle.getArticleId());

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + addArticle.getQuantity());
            cartPersistencePort.updateCartItem(cartItem);
        } else {

            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setArticleId(addArticle.getArticleId());
            newCartItem.setQuantity(addArticle.getQuantity());

            cartPersistencePort.addCartItem(newCartItem);
        }
    }
}
