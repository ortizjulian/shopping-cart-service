package com.emazon.shopping_cart.domain.usecase;


import com.emazon.shopping_cart.domain.api.ISecurityServicePort;
import com.emazon.shopping_cart.domain.spi.ISecurityPersistencePort;

public class SecurityUseCase implements ISecurityServicePort {

    private ISecurityPersistencePort securityPersistencePort;

    public SecurityUseCase(ISecurityPersistencePort securityPersistencePort) {
        this.securityPersistencePort = securityPersistencePort;
    }

    @Override
    public void setToken(String token) {
        securityPersistencePort.setToken(token);
    }

    @Override
    public void removeToken() {
        securityPersistencePort.removeToken();
    }
}
