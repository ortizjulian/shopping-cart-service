package com.emazon.shopping_cart.domain.exceptions;

public class CategoryLimitExceededException extends RuntimeException {
    public CategoryLimitExceededException(String message) {
        super(message);
    }
}
