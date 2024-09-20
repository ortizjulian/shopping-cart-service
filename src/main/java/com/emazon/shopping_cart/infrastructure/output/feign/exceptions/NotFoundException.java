package com.emazon.shopping_cart.infrastructure.output.feign.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

}