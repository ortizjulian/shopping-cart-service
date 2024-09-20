package com.emazon.shopping_cart.domain.exceptions;

public class PaginationParametersInvalidException extends RuntimeException {
    public PaginationParametersInvalidException(String message) {
        super(message);
    }
}