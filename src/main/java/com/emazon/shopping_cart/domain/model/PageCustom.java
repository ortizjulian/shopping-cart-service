package com.emazon.shopping_cart.domain.model;

import java.util.List;

public class PageCustom<T>{

    private List<T> content;
    private Long totalElements;
    private Integer totalPages;
    private Boolean hasNext;
    private Boolean hasPrevious;

    public PageCustom(){
        // This empty constructor allows for the creation of instances
        // of the PageCustom class without needing to provide any parameters.
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}
