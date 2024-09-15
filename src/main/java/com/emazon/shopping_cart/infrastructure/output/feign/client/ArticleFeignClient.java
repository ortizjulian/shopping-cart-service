package com.emazon.shopping_cart.infrastructure.output.feign.client;

import com.emazon.shopping_cart.infrastructure.configuration.feign.FeignClientConfig;
import com.emazon.shopping_cart.utils.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = FeignConstants.FEIGN_ARTICLE_NAME, url =FeignConstants.PATH_ARTICLE_URL,configuration = FeignClientConfig.class)
public interface ArticleFeignClient {
    /*
    @PatchMapping(value = FeignConstants.PATH_ARTICLE_UPDATE_QUANTITY)
    ResponseEntity<Void> updateArticleQuantity(@RequestBody UpdateQuantityFeignDto updateQuantityFeignDto);
*/
}
