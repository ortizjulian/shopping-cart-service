package com.emazon.shopping_cart.infrastructure.output.jpa.repository;

import com.emazon.shopping_cart.infrastructure.output.jpa.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICartRepository extends JpaRepository<CartEntity,Long> {
    boolean existsByUserId(Long userId);
    Optional<CartEntity> getByUserId(Long userId);
}
