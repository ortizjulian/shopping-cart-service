package com.emazon.shopping_cart.domain.spi;

import com.emazon.shopping_cart.domain.model.Article;
import com.emazon.shopping_cart.domain.model.CategoryQuantity;

import java.util.List;

public interface IStockPersistencePort {
    Article getArticleById(Long articleId);
    List<CategoryQuantity> getCategoryQuantities(List<Long> articlesIds);
}
