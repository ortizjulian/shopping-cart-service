package com.emazon.shopping_cart.infrastructure.output.feign.utils;

import com.emazon.shopping_cart.domain.model.StatusCodeEnum;
import com.emazon.shopping_cart.utils.Constants;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class FeignExceptionHandler {

    private FeignExceptionHandler() {
        throw new UnsupportedOperationException(Constants.UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static StatusCodeEnum handleFeignException(FeignException e) {
        HttpStatusCode status = HttpStatusCode.valueOf(e.status());

        if (status == HttpStatus.NOT_FOUND) {
            return StatusCodeEnum.NOT_FOUND;
        } else if (status == HttpStatus.BAD_REQUEST) {
            return StatusCodeEnum.BAD_REQUEST;
        } else if (status == HttpStatus.FORBIDDEN) {
            return StatusCodeEnum.FORBIDDEN;
        } else {
            return StatusCodeEnum.INTERNAL_SERVER_ERROR;
        }
    }
}
