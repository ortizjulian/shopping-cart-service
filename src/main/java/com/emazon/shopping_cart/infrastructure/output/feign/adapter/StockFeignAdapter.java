package com.emazon.shopping_cart.infrastructure.output.feign.adapter;

import com.emazon.shopping_cart.domain.model.Article;
import com.emazon.shopping_cart.domain.model.CategoryQuantity;
import com.emazon.shopping_cart.domain.spi.IStockPersistencePort;
import com.emazon.shopping_cart.infrastructure.output.feign.client.StockFeignClient;
import com.emazon.shopping_cart.infrastructure.output.feign.dto.CategoryQuantityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
public class StockFeignAdapter implements IStockPersistencePort {

    private final StockFeignClient stockFeignClient;

    @Override
    public Article getArticleById(Long articleId) {
        ResponseEntity<Article> response = stockFeignClient.getArticleById(articleId);
        return response.getBody();
    }

    @Override
    public List<CategoryQuantity> getCategoryQuantities(List<Long> articlesIds) {
        CategoryQuantityRequest categoryQuantityRequest = new CategoryQuantityRequest(articlesIds);
        ResponseEntity<List<CategoryQuantity>> response=  stockFeignClient.getCategoryQuantities(categoryQuantityRequest);
        return response.getBody();
    }
}
