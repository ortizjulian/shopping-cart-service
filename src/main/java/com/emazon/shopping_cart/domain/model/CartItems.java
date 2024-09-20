package com.emazon.shopping_cart.domain.model;


public class CartItems {
   private PageCustom<Article> articlePageCustom;
   private Double totalPrice;

   public CartItems(Double totalPrice, PageCustom<Article> articlePageCustom) {
      this.totalPrice = totalPrice;
      this.articlePageCustom = articlePageCustom;
   }

   public PageCustom<Article> getArticlePageCustom() {
      return articlePageCustom;
   }

   public void setArticlePageCustom(PageCustom<Article> articlePageCustom) {
      this.articlePageCustom = articlePageCustom;
   }

   public Double getTotalPrice() {
      return totalPrice;
   }

   public void setTotalPrice(Double totalPrice) {
      this.totalPrice = totalPrice;
   }
}
