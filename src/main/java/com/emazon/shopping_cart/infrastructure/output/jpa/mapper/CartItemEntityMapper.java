package com.emazon.shopping_cart.infrastructure.output.jpa.mapper;

import com.emazon.shopping_cart.domain.model.CartItem;
import com.emazon.shopping_cart.infrastructure.output.jpa.entity.CartItemEntity;
import com.emazon.shopping_cart.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER_SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartItemEntityMapper {
    CartItem toCartItem(CartItemEntity cartItemEntity);
    List<CartItem> toCartItemList(List<CartItemEntity> cartItemEntityList);
    CartItemEntity toCartItemEntity(CartItem cartItem);
}
