package com.emazon.shopping_cart.domain.usecase;


import com.emazon.shopping_cart.domain.exceptions.CategoryLimitExceededException;
import com.emazon.shopping_cart.domain.exceptions.InsufficientStockException;
import com.emazon.shopping_cart.domain.model.*;
import com.emazon.shopping_cart.domain.spi.ICartPersistencePort;
import com.emazon.shopping_cart.domain.spi.IStockPersistencePort;
import com.emazon.shopping_cart.domain.spi.ITransactionPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class CartUseCaseTest {

    @Mock
    private ICartPersistencePort cartPersistencePort;

    @Mock
    private IStockPersistencePort stockPersistencePort;

    @Mock
    private ITransactionPersistencePort iTransactionPersistencePort;

    @InjectMocks
    private CartUseCase cartUseCase;

    private AddArticle addArticle;
    private Cart cart;
    private Article article;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        addArticle = new AddArticle();
        addArticle.setArticleId(1L);
        addArticle.setQuantity(2);

        cart = new Cart();
        cart.setId(1L);
        cart.setUserId(1L);

        article = new Article();
        article.setId(1L);
        article.setQuantity(10);
        article.setCategories(List.of(new Category(1L, "Phone", "Apple")));
    }

    @Test
    void CartUseCase_AddToCart_ShouldAddArticleToCartSuccessfully() {
        CartItem cartItem = new CartItem();
        cartItem.setArticleId(addArticle.getArticleId());
        cartItem.setQuantity(addArticle.getQuantity());

        Mockito.when(cartPersistencePort.getCartByUserId(1L)).thenReturn(Optional.of(cart));
        Mockito.when(stockPersistencePort.getArticleById(addArticle.getArticleId())).thenReturn(article);
        Mockito.when(cartPersistencePort.getCartItem(cart.getId(), addArticle.getArticleId())).thenReturn(null);

        cartUseCase.addToCart(addArticle, 1L);

        Mockito.verify(cartPersistencePort).updateCart(cart);
        Mockito.verify(stockPersistencePort).getArticleById(addArticle.getArticleId());
    }
    @Test
    void CartUseCase_AddToCart_ShouldThrowInsufficientStockException_WhenStockIsInsufficient() {
        article.setQuantity(1);
        LocalDateTime nextSupplyDate = LocalDateTime.now().plusMonths(1);

        Mockito.when(cartPersistencePort.getCartByUserId(1L)).thenReturn(Optional.of(cart));
        Mockito.when(stockPersistencePort.getArticleById(addArticle.getArticleId())).thenReturn(article);
        Mockito.when(iTransactionPersistencePort.nextSupplyDate(addArticle.getArticleId())).thenReturn(nextSupplyDate);

        assertThrows(InsufficientStockException.class, () -> {
            cartUseCase.addToCart(addArticle, 1L);
        });


        Mockito.verify(iTransactionPersistencePort).nextSupplyDate(addArticle.getArticleId());
    }

    @Test
    void CartUseCase_AddToCart_ShouldThrowCategoryLimitExceededException_WhenMaxCategoriesExceeded() {
        List<CategoryQuantity> categoryQuantities = List.of(
                new CategoryQuantity("Phone", 3L)
        );

        Mockito.when(cartPersistencePort.getCartByUserId(1L)).thenReturn(Optional.of(cart));
        Mockito.when(stockPersistencePort.getArticleById(addArticle.getArticleId())).thenReturn(article);
        Mockito.when(cartPersistencePort.getArticleIdsFromCart(cart.getId())).thenReturn(List.of(1L, 2L));
        Mockito.when(stockPersistencePort.getCategoryQuantities(List.of(1L, 2L))).thenReturn(categoryQuantities);

        assertThrows(CategoryLimitExceededException.class, () -> {
            cartUseCase.addToCart(addArticle, 1L);
        });

    }
}