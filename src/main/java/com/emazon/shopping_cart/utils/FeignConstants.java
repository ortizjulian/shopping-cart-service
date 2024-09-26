package com.emazon.shopping_cart.utils;

public class FeignConstants {
    private FeignConstants() {
        throw new UnsupportedOperationException(Constants.UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    //Exceptions
    public static final String BAD_REQUEST = "Bad Request";
    public static final String NOT_FOUND = "Not Found";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error from remote service";

    public static final int BAD_REQUEST_CODE = 400;
    public static final int NOT_FOUND_CODE = 404;

    //Stock
    public static final String FEIGN_TRANSACTION_NAME = "TRANSACTION-API";
    public static final String PATH_TRANSACTION_URL = "${transaction.base-url}";
    public static final String PATH_NEXT_DATE_SUPPLY_URL = "/supply/next/{articleId}";
    //Article
    public static final String FEIGN_STOCK_NAME = "STOCK-API";
    public static final String PATH_ARTICLE_URL = "${stock.base-url}";
    public static final String PATH_ARTICLE_BY_ID = "/article/{articleId}";
    public static final String PATH_ARTICLE_LIST = "/article";
    public static final String PATH_ARTICLE_TOTAL_PRICE = "/article/price";
    public static final String ARTICLE_ID = "articleId";
    //Category
    public static final String PATH_CATEGORY_QUANTITIES = "/category/quantities";

}
