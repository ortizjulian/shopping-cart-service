package com.emazon.shopping_cart.infrastructure.input.rest;

import com.emazon.shopping_cart.application.dto.AddArticleRequest;
import com.emazon.shopping_cart.application.handler.ICartHandler;
import com.emazon.shopping_cart.application.handler.ISecurityHandler;
import com.emazon.shopping_cart.infrastructure.output.security.jwt.JwtTokenManager;
import com.emazon.shopping_cart.utils.SecurityConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
@Validated
public class CartRestController {

    private final ICartHandler cartHandler;
    private final ISecurityHandler securityHandler;
    private final JwtTokenManager tokenManager;

    @Operation(summary = "Add Article to Cart", description = "Adds an article to the user's cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article added to the cart successfully."),
            @ApiResponse(responseCode = "404", description = "Article not found for the given ID."),
            @ApiResponse(responseCode = "409", description = "Conflict due to insufficient stock or maximum categories exceeded."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping("/items")
    public ResponseEntity<Void> addArticleToCart(@Valid @RequestBody AddArticleRequest addArticleRequest,
                                                 @RequestHeader(SecurityConstants.AUTHORIZATION) String token) {
        try {
            Long userId = tokenManager.extractIdFromFullToken(token);
            securityHandler.setToken(token);
            cartHandler.addToCart(addArticleRequest, userId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } finally {
            securityHandler.removeToken();
        }
    }

}
