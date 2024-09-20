package com.emazon.shopping_cart.infrastructure.output.feign.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryQuantityRequest {
    private List<Long> articleIds;
}
