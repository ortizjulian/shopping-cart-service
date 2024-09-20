package com.emazon.shopping_cart.infrastructure.output.feign.client;

import com.emazon.shopping_cart.infrastructure.configuration.feign.FeignClientConfig;
import com.emazon.shopping_cart.utils.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@FeignClient(name = FeignConstants.FEIGN_TRANSACTION_NAME, url =FeignConstants.PATH_TRANSACTION_URL,configuration = FeignClientConfig.class)
public interface TransactionFeignClient {
    @GetMapping(FeignConstants.PATH_NEXT_DATE_SUPPLY_URL)
    LocalDateTime nextSupplyDate(@PathVariable(FeignConstants.ARTICLE_ID) Long articleId);
}


