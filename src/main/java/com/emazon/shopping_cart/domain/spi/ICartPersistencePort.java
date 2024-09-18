package com.emazon.shopping_cart.domain.spi;

public interface ICartPersistencePort {
    boolean userHasCart(Long userId);
    void createCart(Long userId);
}
