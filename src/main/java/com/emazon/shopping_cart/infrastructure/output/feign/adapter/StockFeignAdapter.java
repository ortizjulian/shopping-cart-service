package com.emazon.shopping_cart.infrastructure.output.feign.adapter;

import com.emazon.shopping_cart.domain.model.Article;
import com.emazon.shopping_cart.domain.model.CategoryQuantity;
import com.emazon.shopping_cart.domain.model.PageCustom;
import com.emazon.shopping_cart.domain.spi.IStockPersistencePort;
import com.emazon.shopping_cart.infrastructure.output.feign.client.StockFeignClient;
import com.emazon.shopping_cart.infrastructure.output.feign.dto.ArticleListRequest;
import com.emazon.shopping_cart.infrastructure.output.feign.dto.PriceDto;
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
    public List<CategoryQuantity> getCategoryQuantities(List<Long> articleIds) {
        ArticleListRequest articleListRequest = new ArticleListRequest(articleIds);
        ResponseEntity<List<CategoryQuantity>> response=  stockFeignClient.getCategoryQuantities(articleListRequest);
        return response.getBody();
    }

    @Override
    public PageCustom<Article> getArticlesByIds(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName,List<Long> articleIds) {
        ResponseEntity<PageCustom<Article>> response = stockFeignClient.getAllArticles(page, size, sortDirection, sortBy, brandName, categoryName, articleIds);
        return response.getBody();
    }

    @Override
    public Double getTotalPriceByArticleIds(List<Long> articleIds) {
        ArticleListRequest articleListRequest = new ArticleListRequest(articleIds);
        ResponseEntity<PriceDto> response=  stockFeignClient.getTotalPriceByArticleIds(articleListRequest);
        return response.getBody().getTotalPrice();
    }
}
