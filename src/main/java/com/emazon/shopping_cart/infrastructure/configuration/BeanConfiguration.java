package com.emazon.shopping_cart.infrastructure.configuration;

import com.emazon.shopping_cart.domain.api.ICartServicePort;
import com.emazon.shopping_cart.domain.spi.ICartPersistencePort;
import com.emazon.shopping_cart.domain.usecase.CartUseCase;
import com.emazon.shopping_cart.infrastructure.output.jpa.adapter.CartJpaAdapter;
import com.emazon.shopping_cart.infrastructure.output.jpa.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICartRepository cartRepository;

    @Bean
    public ICartPersistencePort cartPersistencePort(){
        return new CartJpaAdapter(cartRepository);
    }

    @Bean
    public ICartServicePort cartServicePort(){
        return new CartUseCase(cartPersistencePort());
    }
}
