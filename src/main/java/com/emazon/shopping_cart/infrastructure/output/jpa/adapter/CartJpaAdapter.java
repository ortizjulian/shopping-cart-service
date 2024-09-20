package com.emazon.shopping_cart.infrastructure.output.jpa.adapter;

import com.emazon.shopping_cart.domain.model.Cart;
import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.domain.spi.ICartPersistencePort;
import com.emazon.shopping_cart.infrastructure.output.jpa.entity.CartEntity;
import com.emazon.shopping_cart.infrastructure.output.jpa.entity.CartItemEntity;
import com.emazon.shopping_cart.infrastructure.output.jpa.mapper.CartEntityMapper;
import com.emazon.shopping_cart.infrastructure.output.jpa.mapper.CartItemEntityMapper;
import com.emazon.shopping_cart.infrastructure.output.jpa.repository.ICartItemRepository;
import com.emazon.shopping_cart.infrastructure.output.jpa.repository.ICartRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CartJpaAdapter implements ICartPersistencePort {

    private final ICartRepository cartRepository;
    private final ICartItemRepository cartItemRepository;
    private final CartEntityMapper cartEntityMapper;
    private final CartItemEntityMapper cartItemEntityMapper;

    @Override
    public Cart createCart(Long userId) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUserId(userId);
        cartEntity = cartRepository.save(cartEntity);
        return cartEntityMapper.toCart(cartEntity);
    }

    @Override
    public Optional<Cart> getCartByUserId(Long userId) {
        Optional<CartEntity> cart = cartRepository.getByUserId(userId);
        return cart.map(cartEntityMapper::toCart);
    }

    @Override
    public CartItem getCartItem(Long cartId, Long articleId) {
        CartItemEntity cartItemEntity =  cartItemRepository.findByCartIdAndArticleId(cartId, articleId);
        return cartItemEntityMapper.toCartItem(cartItemEntity);
    }

    @Override
    public void saveCartItem(CartItem cartItem) {
        CartItemEntity cartItemEntity = cartItemEntityMapper.toCartItemEntity(cartItem);
        cartItemRepository.save(cartItemEntity);
    }

    @Override
    public List<Long> getArticleIdsFromCart(Long cartId) {
        Optional<List<CartItemEntity>> cartItems = cartItemRepository.findByCartId(cartId);

        return cartItems.map(items -> items.stream()
                        .map(CartItemEntity::getArticleId)
                        .toList())
                .orElse(Collections.emptyList());
    }

    @Override
    public void updateCart(Cart cart) {
        CartEntity cartEntity = cartEntityMapper.toCartEntity(cart);
        cartRepository.save(cartEntity);
    }

    @Override
    public void deleteItem(Long articleId, Long userId) {
        cartItemRepository.deleteByArticleIdAndCartUserId(articleId,userId);
    }

    @Override
    public List<CartItem> getCartItemsFromUserId(Long userId) {
        Optional<List<CartItemEntity>> cartItems = cartItemRepository.findByCartUserId(userId);
        if (cartItems.isEmpty()) {
            return Collections.emptyList();
        }
        return cartItemEntityMapper.toCartItemList(cartItems.get());
    }


}
