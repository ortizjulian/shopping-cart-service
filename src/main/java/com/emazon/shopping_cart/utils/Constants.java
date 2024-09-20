package com.emazon.shopping_cart.utils;

public class Constants {
    private Constants() {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Utility class should not be instantiated";

    public static final String MAPPER_SPRING = "spring";
    public static final String RESPONSE_MESSAGE_KEY = "Message";

    public static final String EXCEPTION_SERVICE_UNAVAILABLE = "The service is currently unavailable. Please try again later.";

    //ArticleExceptions
    public static final String EXCEPTION_ARTICLE_ID_NULL = "Article ID cannot be null";
    public static final String EXCEPTION_ARTICLE_QUANTITY_NULL = "Quantity cannot be null";
    public static final String EXCEPTION_ARTICLE_QUANTITY_POSITIVE = "Quantity must be a positive number";
    public static final String STOCK_INSUFFICIENT_EXCEPTION = "Insufficient stock available. Only ";
    public static final String NEXT_SUPPLY_EXCEPTION = ", Next supply date on: ";

    // Constants for validation
    public static final Long ZERO = 0L;
    public static final Integer ONE = 1;
    public static final Integer MAX_CATEGORIES_ALLOWED = 3;

    // Error messages
    public static final String CATEGORY_LIMIT_EXCEEDED_MESSAGE = "Exceeds maximum allowed categories for: ";
}
