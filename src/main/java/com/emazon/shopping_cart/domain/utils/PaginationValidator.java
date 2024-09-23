package com.emazon.shopping_cart.domain.utils;


import com.emazon.shopping_cart.domain.exceptions.PaginationParametersInvalidException;
import com.emazon.shopping_cart.utils.Constants;

public class PaginationValidator {

    private PaginationValidator() {
        throw new UnsupportedOperationException(Constants.UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static void validatePagination(Integer page, Integer size, String sortDirection) {
        if (page < Constants.PAGINATION_ZERO) {
            throw new PaginationParametersInvalidException(Constants.EXCEPTION_PAGE_NUMBER_NEGATIVE);
        }
        if (size < Constants.PAGINATION_ZERO) {
            throw new PaginationParametersInvalidException(Constants.EXCEPTION_PAGE_SIZE_NEGATIVE);
        }
        if (sortDirection == null || sortDirection.isEmpty() ||
                (!Constants.SORT_DIRECTION_ASC.equals(sortDirection) && !Constants.SORT_DIRECTION_DESC.equals(sortDirection))) {
            throw new PaginationParametersInvalidException(Constants.EXCEPTION_SORT_DIRECTION_INVALID);
        }
    }
}

