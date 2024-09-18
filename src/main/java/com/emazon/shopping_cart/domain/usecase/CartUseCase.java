package com.emazon.shopping_cart.domain.usecase;

import com.emazon.shopping_cart.domain.api.ICartServicePort;
import com.emazon.shopping_cart.domain.model.AddArticle;
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
    }
}
