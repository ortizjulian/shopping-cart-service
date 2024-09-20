package com.emazon.shopping_cart.infrastructure.output.jpa.mapper;

import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.infrastructure.output.jpa.entity.CartItemEntity;
import com.emazon.shopping_cart.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Constants.MAPPER_SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartItemEntityMapper {
    CartItem toCartItem(CartItemEntity cartItemEntity);
    CartItemEntity toCartItemEntity(CartItem cartItem);
}
