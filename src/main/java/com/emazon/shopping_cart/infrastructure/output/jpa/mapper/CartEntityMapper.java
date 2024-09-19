package com.emazon.shopping_cart.infrastructure.output.jpa.mapper;

import com.emazon.shopping_cart.domain.model.Cart;
import com.emazon.shopping_cart.infrastructure.output.jpa.entity.CartEntity;
import com.emazon.shopping_cart.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Constants.MAPPER_SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartEntityMapper {
    Cart toCart(CartEntity cartEntity);
}