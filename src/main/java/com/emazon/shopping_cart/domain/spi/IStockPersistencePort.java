package com.emazon.shopping_cart.domain.spi;

import com.emazon.shopping_cart.domain.model.Article;
import com.emazon.shopping_cart.domain.model.CategoryQuantity;
import com.emazon.shopping_cart.domain.model.PageCustom;

import java.util.List;

public interface IStockPersistencePort {
    Article getArticleById(Long articleId);
    List<CategoryQuantity> getCategoryQuantities(List<Long> articleIds);
    PageCustom<Article> getArticlesByIds(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName,List<Long> articleIds);
}
