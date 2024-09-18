package com.emazon.shopping_cart.infrastructure.input.rest;

import com.emazon.shopping_cart.application.dto.AddArticleRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article")
@RequiredArgsConstructor
@Validated
public class CartRestController {
    @PostMapping
    public ResponseEntity<Void> addArticleToCart(@Valid AddArticleRequest addArticleRequest){

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
