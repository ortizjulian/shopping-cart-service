package com.emazon.shopping_cart.domain.model;


public class CartItems {
   private PageCustom<Article> articles;
   private String totalPrice;

   public CartItems(String totalPrice, PageCustom<Article> articles) {
      this.totalPrice = totalPrice;
      this.articles = articles;
   }

   public PageCustom<Article> getArticles() {
      return articles;
   }

   public void setArticlePageCustom(PageCustom<Article> articles) {
      this.articles = articles;
   }

   public String getTotalPrice() {
      return totalPrice;
   }

   public void setTotalPrice(String totalPrice) {
      this.totalPrice = totalPrice;
   }
}
