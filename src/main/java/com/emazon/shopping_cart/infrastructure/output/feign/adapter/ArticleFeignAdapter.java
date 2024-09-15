package com.emazon.shopping_cart.infrastructure.output.feign.adapter;

import com.emazon.shopping_cart.domain.spi.IArticlePersistencePort;
import com.emazon.shopping_cart.infrastructure.output.feign.client.ArticleFeignClient;
import com.emazon.shopping_cart.infrastructure.output.feign.mapper.UpdateQuantityFeignMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleFeignAdapter implements IArticlePersistencePort {

    private final ArticleFeignClient articleFeignClient;
    private final UpdateQuantityFeignMapper updateQuantityFeignMapper;


}
