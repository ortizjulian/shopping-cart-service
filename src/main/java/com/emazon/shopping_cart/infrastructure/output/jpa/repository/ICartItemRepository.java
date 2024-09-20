package com.emazon.shopping_cart.infrastructure.output.jpa.repository;

import com.emazon.shopping_cart.infrastructure.output.jpa.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICartItemRepository extends JpaRepository<CartItemEntity,Long> {
    CartItemEntity findByCartIdAndArticleId(Long cartId, Long articleId);
    Optional<List<CartItemEntity>> findByCartId(Long cartId);
    void deleteByArticleIdAndCartUserId(Long articleId, Long userId);
}
