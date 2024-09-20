package com.emazon.shopping_cart.infrastructure.output.feign.client;

import com.emazon.shopping_cart.domain.model.Article;
import com.emazon.shopping_cart.domain.model.CategoryQuantity;
import com.emazon.shopping_cart.domain.model.PageCustom;
import com.emazon.shopping_cart.infrastructure.configuration.feign.FeignClientConfig;
import com.emazon.shopping_cart.infrastructure.output.feign.dto.ArticleListRequest;
import com.emazon.shopping_cart.utils.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = FeignConstants.FEIGN_STOCK_NAME, url =FeignConstants.PATH_ARTICLE_URL,configuration = FeignClientConfig.class)
public interface StockFeignClient {
    @GetMapping(FeignConstants.PATH_ARTICLE_CHECK_STOCK)
    ResponseEntity<Article> getArticleById(@PathVariable(FeignConstants.ARTICLE_ID) Long articleId);

    @PostMapping(FeignConstants.PATH_CATEGORY_QUANTITIES)
    ResponseEntity<List<CategoryQuantity>> getCategoryQuantities(
            @RequestBody ArticleListRequest articleListRequest);

    @GetMapping(FeignConstants.PATH_ARTICLE_LIST)
    ResponseEntity<PageCustom<Article>> getAllArticles(
           @RequestParam Integer page,
           @RequestParam Integer size,
           @RequestParam String sortDirection,
           @RequestParam String sortBy,
           @RequestParam String brandName,
           @RequestParam String categoryName,
           @RequestParam List<Long> articleIds
    );
}
