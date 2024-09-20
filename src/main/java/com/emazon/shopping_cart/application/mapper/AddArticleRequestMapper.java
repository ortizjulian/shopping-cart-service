package com.emazon.shopping_cart.application.mapper;

import com.emazon.shopping_cart.utils.Constants;
import com.emazon.shopping_cart.application.dto.AddArticleRequest;
import com.emazon.shopping_cart.domain.model.AddArticle;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Constants.MAPPER_SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AddArticleRequestMapper {
    AddArticle addArticleRequestToAddArticle(AddArticleRequest addArticleRequest);
}