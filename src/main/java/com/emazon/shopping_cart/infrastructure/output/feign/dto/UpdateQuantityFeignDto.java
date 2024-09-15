package com.emazon.shopping_cart.infrastructure.output.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateQuantityFeignDto {
    private Long articleId;
    private Integer quantity;
}
