package com.emazon.shopping_cart.application.handler;

import com.emazon.shopping_cart.application.dto.AddArticleRequest;
import com.emazon.shopping_cart.application.mapper.AddArticleRequestMapper;
import com.emazon.shopping_cart.domain.api.ICartServicePort;
import com.emazon.shopping_cart.domain.model.AddArticle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartHandler implements ICartHandler {

    private final ICartServicePort cartServicePort;
    private final AddArticleRequestMapper addArticleRequestMapper;
    @Override
    public void addToCart(AddArticleRequest addArticleRequest,Long userId) {
        AddArticle addArticle = addArticleRequestMapper.addArticleRequestToAddArticle(addArticleRequest);
        cartServicePort.addToCart(addArticle, userId);
    }

    @Override
    public void deleteItem(Long articleId, Long userId) {
        cartServicePort.deleteItem(articleId,userId);
    }
}
