package com.emazon.shopping_cart.infrastructure.output.jpa.adapter;

import com.emazon.shopping_cart.domain.spi.ICartPersistencePort;
import com.emazon.shopping_cart.infrastructure.output.jpa.entity.CartEntity;
import com.emazon.shopping_cart.infrastructure.output.jpa.repository.ICartRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartJpaAdapter implements ICartPersistencePort {

    private final ICartRepository cartRepository;

    @Override
    public boolean userHasCart(Long userId) {
        return cartRepository.existByUserId(userId);
    }

    @Override
    public void createCart(Long userId) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(userId);
        cartRepository.save(cartEntity);
    }
}
