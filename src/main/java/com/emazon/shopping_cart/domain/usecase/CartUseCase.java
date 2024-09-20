package com.emazon.shopping_cart.domain.usecase;

import com.emazon.shopping_cart.domain.api.ICartServicePort;
import com.emazon.shopping_cart.domain.exceptions.CategoryLimitExceededException;
import com.emazon.shopping_cart.domain.exceptions.InsufficientStockException;
import com.emazon.shopping_cart.domain.model.*;
import com.emazon.shopping_cart.domain.spi.ICartPersistencePort;
import com.emazon.shopping_cart.domain.spi.IStockPersistencePort;
import com.emazon.shopping_cart.domain.spi.ITransactionPersistencePort;
import com.emazon.shopping_cart.utils.Constants;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartUseCase implements ICartServicePort {

    private final ICartPersistencePort cartPersistencePort;
    private final IStockPersistencePort stockPersistencePort;
    private final ITransactionPersistencePort iTransactionPersistencePort;

    public CartUseCase(ICartPersistencePort cartPersistencePort, IStockPersistencePort stockPersistencePort, ITransactionPersistencePort iTransactionPersistencePort) {
        this.cartPersistencePort = cartPersistencePort;
        this.stockPersistencePort = stockPersistencePort;
        this.iTransactionPersistencePort = iTransactionPersistencePort;
    }

    private void checkArticleStockAvailability(AddArticle addArticle, Integer stockAvailable) {
        if(stockAvailable < addArticle.getQuantity()) {
            LocalDateTime nextSupplyDate = this.iTransactionPersistencePort.nextSupplyDate(addArticle.getArticleId());

            throw new InsufficientStockException(Constants.STOCK_INSUFFICIENT_EXCEPTION + stockAvailable + Constants.NEXT_SUPPLY_EXCEPTION + nextSupplyDate);
        }
    }

    private void validateMaxCategories(Long cartId, List<Category> categories) {
        List<Long> articleIds = cartPersistencePort.getArticleIdsFromCart(cartId);

        List<CategoryQuantity> categoryQuantities = stockPersistencePort.getCategoryQuantities(articleIds);

        Map<String, Long> currentCategoryCounts = categoryQuantities.stream()
                .collect(Collectors.toMap(CategoryQuantity::getCategoryName, CategoryQuantity::getQuantity));


        for (Category category : categories) {
            Long newCategoryCount = currentCategoryCounts.getOrDefault(category.getName(), Constants.ZERO) + Constants.ONE;

            if (newCategoryCount > Constants.MAX_CATEGORIES_ALLOWED) {
                throw new CategoryLimitExceededException(Constants.CATEGORY_LIMIT_EXCEEDED_MESSAGE + category.getName());
            }
        }
    }

    @Override
    public void addToCart(AddArticle addArticle,Long userId) {

        Optional<Cart> optionalCart = cartPersistencePort.getCartByUserId(userId);
        Cart cart;

        cart = optionalCart.orElseGet(() -> cartPersistencePort.createCart(userId));

        CartItem cartItem = cartPersistencePort.getCartItem(cart.getId(), addArticle.getArticleId());

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + addArticle.getQuantity());
            addArticle.setQuantity(addArticle.getQuantity() + cartItem.getQuantity());
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setArticleId(addArticle.getArticleId());
            cartItem.setQuantity(addArticle.getQuantity());
        }
        Article article = stockPersistencePort.getArticleById(addArticle.getArticleId());

        this.checkArticleStockAvailability(addArticle, article.getQuantity());

        this.validateMaxCategories(cart.getId(), article.getCategories());

        cart.setUpdatedDate(LocalDateTime.now());
        cartPersistencePort.updateCart(cart);
        cartPersistencePort.saveCartItem(cartItem);
    }
}
