package com.emazon.shopping_cart.utils;

public class Constants {
    private Constants() {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Utility class should not be instantiated";

    public static final String MAPPER_SPRING = "spring";
    public static final String RESPONSE_MESSAGE_KEY = "Message";
    public static final Double ZERO_DOUBLE = 0D;
    public static final String ZERO_STRING = "";
    public static final String DECIMAL_PATTERN = "#,##0.00";
    public static final String EXCEPTION_SERVICE_UNAVAILABLE = "The service is currently unavailable. Please try again later.";
    // Pagination defaults
    public static final Integer PAGINATION_ZERO = 0;
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";
    public static final String DEFAULT_SORT_DIRECTION = "ASC";
    public static final String DEFAULT_SORT_BY = "name";
    public static final String SORT_DIRECTION_ASC = "ASC";
    public static final String SORT_DIRECTION_DESC = "DESC";
    public static final String DEFAULT_BRAND_NAME = "";
    public static final String DEFAULT_CATEGORY_NAME = "";
    //Pagination Exceptions
    public static final String EXCEPTION_PAGE_NUMBER_NEGATIVE = "Page number cannot be negative.";
    public static final String EXCEPTION_PAGE_SIZE_NEGATIVE = "Page size cannot be negative.";
    public static final String EXCEPTION_SORT_DIRECTION_INVALID = "Sort direction must be 'ASC' or 'DESC'.";


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
