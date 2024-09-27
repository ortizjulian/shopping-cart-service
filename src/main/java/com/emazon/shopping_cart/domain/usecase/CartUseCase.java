package com.emazon.shopping_cart.domain.usecase;

import com.emazon.shopping_cart.domain.api.ICartServicePort;
import com.emazon.shopping_cart.domain.exceptions.CartItemNotFoundException;
import com.emazon.shopping_cart.domain.exceptions.CategoryLimitExceededException;
import com.emazon.shopping_cart.domain.exceptions.InsufficientStockException;
import com.emazon.shopping_cart.domain.model.*;
import com.emazon.shopping_cart.domain.spi.ICartPersistencePort;
import com.emazon.shopping_cart.domain.spi.IStockPersistencePort;
import com.emazon.shopping_cart.domain.spi.ITransactionPersistencePort;
import com.emazon.shopping_cart.domain.utils.PaginationValidator;
import com.emazon.shopping_cart.utils.Constants;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CartUseCase implements ICartServicePort {

    private ICartPersistencePort cartPersistencePort;
    private IStockPersistencePort stockPersistencePort;
    private ITransactionPersistencePort iTransactionPersistencePort;

    public CartUseCase(ICartPersistencePort cartPersistencePort, IStockPersistencePort stockPersistencePort, ITransactionPersistencePort iTransactionPersistencePort) {
        this.cartPersistencePort = cartPersistencePort;
        this.stockPersistencePort = stockPersistencePort;
        this.iTransactionPersistencePort = iTransactionPersistencePort;
    }

    private void checkArticleStockAvailability(AddArticle addArticle, Integer stockAvailable) {
        if(stockAvailable < addArticle.getQuantity()) {
            LocalDateTime nextSupplyDate = this.iTransactionPersistencePort.nextSupplyDate(addArticle.getArticleId());

            throw new InsufficientStockException(Constants.STOCK_INSUFFICIENT_EXCEPTION + stockAvailable + Constants.NEXT_SUPPLY_EXCEPTION + nextSupplyDate.toLocalDate());
        }
    }

    private void validateMaxCategories(Long cartId, List<Category> categories) {
        List<Long> articleIds = cartPersistencePort.getArticleIdsFromCart(cartId);

        if(articleIds.isEmpty()) {
            return;
        }

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

        Article article = stockPersistencePort.getArticleById(addArticle.getArticleId());

        CartItem cartItem = cartPersistencePort.getCartItem(cart.getId(), addArticle.getArticleId());
        if (cartItem != null) {
            Integer currentQuantity = cartItem.getQuantity();
            cartItem.setQuantity(currentQuantity + addArticle.getQuantity());
            addArticle.setQuantity(addArticle.getQuantity() + currentQuantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setArticleId(addArticle.getArticleId());
            cartItem.setQuantity(addArticle.getQuantity());
            this.validateMaxCategories(cart.getId(), article.getCategories());
        }

        this.checkArticleStockAvailability(addArticle, article.getQuantity());

        cart.setUpdatedDate(LocalDateTime.now());
        cartPersistencePort.updateCart(cart);
        cartPersistencePort.saveCartItem(cartItem);
    }

    @Override
    public void deleteItem(Long articleId, Long userId) {
        Optional<Cart> optionalCart = cartPersistencePort.getCartByUserId(userId);
        if(optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setUpdatedDate(LocalDateTime.now());
            cartPersistencePort.updateCart(cart);

            cartPersistencePort.deleteItem(articleId,userId);
        }
    }

    @Override
    public CartItems getAllItems(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName, Long userId) {

        PaginationValidator.validatePagination(page,size,sortDirection);

        List<CartItem> cartItems = cartPersistencePort.getCartItemsFromUserId(userId);
        if(cartItems.isEmpty()) {
            return new CartItems(Constants.ZERO_STRING, new PageCustom<>());
        }

        List<Long> articleIds = cartItems.stream()
                .map(CartItem::getArticleId)
                .toList();

        PageCustom<Article> articles = stockPersistencePort.getArticlesByIds(page,size,sortDirection,sortBy,brandName,categoryName,articleIds);

        for (Article article : articles.getContent()) {
            updateArticleCartInfo(article, cartItems);
        }

        String totalPriceString = calculatePrice(articleIds);

        return new CartItems(totalPriceString, articles);
    }

    private String calculatePrice(List<Long> articlesIds){
        Double totalPrice = stockPersistencePort.getTotalPriceByArticleIds(articlesIds);

        DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_PATTERN);
        return decimalFormat.format(totalPrice);
    }

    private void updateArticleCartInfo(Article article, List<CartItem> cartItems) {
        CartItem matchingCartItem = cartItems.stream()
                .filter(cartItem -> cartItem.getArticleId().equals(article.getId()))
                .findFirst()
                .orElse(null);

        if (matchingCartItem == null) {
            throw new CartItemNotFoundException(Constants.EXCEPTION_CART_ITEM_NOT_FOUND + article.getId());
        }

        article.setCartQuantity(matchingCartItem.getQuantity());

        if (article.getQuantity() < matchingCartItem.getQuantity()) {
            LocalDateTime nextSupplyDate = this.iTransactionPersistencePort.nextSupplyDate(article.getId());
            article.setNextRestockDate(nextSupplyDate);
        }
    }

}
