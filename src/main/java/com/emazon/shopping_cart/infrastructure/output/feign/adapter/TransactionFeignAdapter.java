package com.emazon.shopping_cart.infrastructure.output.feign.adapter;

import com.emazon.shopping_cart.domain.spi.ITransactionPersistencePort;
import com.emazon.shopping_cart.infrastructure.output.feign.client.TransactionFeignClient;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class TransactionFeignAdapter implements ITransactionPersistencePort {

    private final TransactionFeignClient transactionFeignClient;

    @Override
    public LocalDateTime nextSupplyDate(Long articleId) {
        return transactionFeignClient.nextSupplyDate(articleId);
    }
}
