package com.emazon.shopping_cart.application.handler;

public interface ISecurityHandler {
    void setToken(String token);
    void removeToken();
}
