package com.emazon.shopping_cart.infrastructure.configuration;

import com.emazon.shopping_cart.domain.api.ICartServicePort;
import com.emazon.shopping_cart.domain.api.ISecurityServicePort;
import com.emazon.shopping_cart.domain.spi.ICartPersistencePort;
import com.emazon.shopping_cart.domain.spi.ISecurityPersistencePort;
import com.emazon.shopping_cart.domain.spi.IStockPersistencePort;
import com.emazon.shopping_cart.domain.spi.ITransactionPersistencePort;
import com.emazon.shopping_cart.domain.usecase.CartUseCase;
import com.emazon.shopping_cart.domain.usecase.SecurityUseCase;
import com.emazon.shopping_cart.infrastructure.output.feign.adapter.StockFeignAdapter;
import com.emazon.shopping_cart.infrastructure.output.feign.adapter.TransactionFeignAdapter;
import com.emazon.shopping_cart.infrastructure.output.feign.client.StockFeignClient;
import com.emazon.shopping_cart.infrastructure.output.feign.client.TransactionFeignClient;
import com.emazon.shopping_cart.infrastructure.output.jpa.adapter.CartJpaAdapter;
import com.emazon.shopping_cart.infrastructure.output.jpa.mapper.CartEntityMapper;
import com.emazon.shopping_cart.infrastructure.output.jpa.mapper.CartItemEntityMapper;
import com.emazon.shopping_cart.infrastructure.output.jpa.repository.ICartItemRepository;
import com.emazon.shopping_cart.infrastructure.output.jpa.repository.ICartRepository;
import com.emazon.shopping_cart.infrastructure.output.security.adapter.SecurityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICartRepository cartRepository;
    private final ICartItemRepository cartItemRepository;
    private final CartEntityMapper cartEntityMapper;
    private final CartItemEntityMapper cartItemEntityMapper;
    private final StockFeignClient stockFeignClient;
    private final TransactionFeignClient transactionFeignClient;

    @Bean
    public ICartPersistencePort cartPersistencePort(){
        return new CartJpaAdapter(cartRepository,cartItemRepository,cartEntityMapper,cartItemEntityMapper);
    }

    @Bean
    public IStockPersistencePort stockPersistencePort(){
        return new StockFeignAdapter(stockFeignClient);
    }

    @Bean
    public ICartServicePort cartServicePort(){
        return new CartUseCase(cartPersistencePort(), stockPersistencePort(), transactionPersistencePort());
    }

    @Bean
    public ISecurityServicePort securityServicePort() {
        return new SecurityUseCase(securityPersistencePort());
    }

    @Bean
    public ITransactionPersistencePort transactionPersistencePort() {
        return new TransactionFeignAdapter(transactionFeignClient);
    }

    @Bean
    public ISecurityPersistencePort securityPersistencePort(){
        return new SecurityAdapter();
    }
}
