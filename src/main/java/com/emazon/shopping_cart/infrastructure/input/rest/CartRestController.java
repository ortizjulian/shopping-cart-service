package com.emazon.shopping_cart.infrastructure.input.rest;

import com.emazon.shopping_cart.application.dto.AddArticleRequest;
import com.emazon.shopping_cart.application.handler.ICartHandler;
import com.emazon.shopping_cart.infrastructure.output.security.jwt.JwtTokenManager;
import com.emazon.shopping_cart.utils.SecurityConstants;
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
    private final JwtTokenManager tokenManager;
    @PostMapping("/items")
    public ResponseEntity<Void> addArticleToCart(@Valid @RequestBody AddArticleRequest addArticleRequest,
                                                 @RequestHeader(SecurityConstants.AUTHORIZATION) String token){
        Long userId = tokenManager.extractIdFromFullToken(token);
        cartHandler.addToCart(addArticleRequest,userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
