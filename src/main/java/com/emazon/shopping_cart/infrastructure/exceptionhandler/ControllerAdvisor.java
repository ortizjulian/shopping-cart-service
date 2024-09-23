package com.emazon.shopping_cart.infrastructure.exceptionhandler;

import com.emazon.shopping_cart.domain.exceptions.CategoryLimitExceededException;
import com.emazon.shopping_cart.domain.exceptions.InsufficientStockException;
import com.emazon.shopping_cart.domain.exceptions.PaginationParametersInvalidException;
import com.emazon.shopping_cart.infrastructure.output.feign.exceptions.BadRequestException;
import com.emazon.shopping_cart.infrastructure.output.feign.exceptions.InternalServerErrorException;
import com.emazon.shopping_cart.infrastructure.output.feign.exceptions.NotFoundException;
import com.emazon.shopping_cart.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = Constants.RESPONSE_MESSAGE_KEY;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> handleBadRequestException(
            BadRequestException badRequestException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, badRequestException.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(
            NotFoundException notFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, notFoundException.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Map<String, String>> handleInternalServerException(
            InternalServerErrorException internalServerErrorException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE, internalServerErrorException.getMessage()));
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Map<String, String>> handleInsufficientStockException(
            InsufficientStockException insufficientStockException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, insufficientStockException.getMessage()));
    }

    @ExceptionHandler(CategoryLimitExceededException.class)
    public ResponseEntity<Map<String, String>> handleCategoryLimitException(
            CategoryLimitExceededException categoryLimitException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, categoryLimitException.getMessage()));
    }


    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Map<String, String>> handleConnectException(
            ConnectException connectExceptionException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE,ExceptionResponse.SERVICE_UNAVAILABLE.getMessage()));
    }

    @ExceptionHandler(PaginationParametersInvalidException.class)
    public ResponseEntity<Map<String, String>> handlePaginationParametersInvalidException(
            PaginationParametersInvalidException paginationParametersInvalidException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, paginationParametersInvalidException.getMessage()));
    }
}
