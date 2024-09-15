package com.emazon.shopping_cart.infrastructure.output.feign.mapper;

import com.emazon.shopping_cart.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Constants.MAPPER_SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UpdateQuantityFeignMapper {

    UpdateQuantityFeignDto toFeignDto(UpdateQuantity updateQuantity);
}
