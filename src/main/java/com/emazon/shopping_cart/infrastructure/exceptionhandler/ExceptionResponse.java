package com.emazon.shopping_cart.infrastructure.exceptionhandler;


import com.emazon.shopping_cart.utils.Constants;

public enum ExceptionResponse {
    SERVICE_UNAVAILABLE(Constants.EXCEPTION_SERVICE_UNAVAILABLE);
    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}