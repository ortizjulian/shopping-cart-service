package com.emazon.shopping_cart.infrastructure.output.jpa.adapter;

import com.emazon.shopping_cart.domain.model.Cart;
import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.domain.spi.ICartPersistencePort;
import com.emazon.shopping_cart.infrastructure.output.jpa.entity.CartEntity;
import com.emazon.shopping_cart.infrastructure.output.jpa.mapper.CartEntityMapper;
import com.emazon.shopping_cart.infrastructure.output.jpa.repository.ICartRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartJpaAdapter implements ICartPersistencePort {

    private final ICartRepository cartRepository;
    private final CartEntityMapper cartEntityMapper;
    @Override
    public boolean userHasCart(Long userId) {
        return cartRepository.existsByUserId(userId);
    }

    @Override
    public void createCart(Long userId) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUserId(userId);
        cartRepository.save(cartEntity);
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        CartEntity cart = cartRepository.getByUserId(userId);
        return cartEntityMapper.toCart(cart);
    }

    @Override
    public CartItem getCartItem(Long cartId, Long userId) {
        return null;
    }

    @Override
    public void updateCartItem(CartItem cartItem) {

    }

    @Override
    public void addCartItem(CartItem cartItem) {

    }
}
