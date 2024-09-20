package com.emazon.shopping_cart.infrastructure.input.rest;

import com.emazon.shopping_cart.application.dto.AddArticleRequest;
import com.emazon.shopping_cart.application.handler.ICartHandler;
import com.emazon.shopping_cart.application.handler.ISecurityHandler;
import com.emazon.shopping_cart.domain.model.CartItems;
import com.emazon.shopping_cart.domain.model.PageCustom;
import com.emazon.shopping_cart.infrastructure.output.security.jwt.JwtTokenManager;
import com.emazon.shopping_cart.utils.Constants;
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

    @Operation(summary = "Retrieve all Items in cart", description = "Returns a list of all items in user cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of items"),
    })
    @GetMapping
    public ResponseEntity<CartItems> getAllArticles(
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = Constants.DEFAULT_SIZE) Integer size,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_DIRECTION) String sortDirection,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(defaultValue = Constants.DEFAULT_BRAND_NAME) String brandName,
            @RequestParam(defaultValue = Constants.DEFAULT_CATEGORY_NAME) String categoryName,
            @RequestHeader(SecurityConstants.AUTHORIZATION) String token

    ){
        Long userId = tokenManager.extractIdFromFullToken(token);
        return ResponseEntity.ok(cartHandler.getAllItems(page,size,sortDirection,sortBy,brandName,categoryName,userId));
    }

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

    @Operation(summary = "Delete a article from cart", description = "Removes a specified article from the user's cart based on the article ID and user token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Article deleted from cart successfully"),
    })
    @DeleteMapping("/items/{articleId}")
    public ResponseEntity<Void> deleteItemFromCart(@PathVariable Long articleId,
                                                      @RequestHeader(SecurityConstants.AUTHORIZATION) String token){
        Long userId = tokenManager.extractIdFromFullToken(token);
        cartHandler.deleteItem(articleId,userId);
        return ResponseEntity.noContent().build();
    }

}
