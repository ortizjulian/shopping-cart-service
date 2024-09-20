package com.emazon.shopping_cart.domain.api;

public interface ISecurityServicePort {
    void setToken(String token);
    void removeToken();
}
