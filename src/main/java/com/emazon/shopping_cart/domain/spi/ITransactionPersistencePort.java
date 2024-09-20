package com.emazon.shopping_cart.domain.spi;

import java.time.LocalDateTime;

public interface ITransactionPersistencePort {
    LocalDateTime nextSupplyDate(Long articleId);
}
