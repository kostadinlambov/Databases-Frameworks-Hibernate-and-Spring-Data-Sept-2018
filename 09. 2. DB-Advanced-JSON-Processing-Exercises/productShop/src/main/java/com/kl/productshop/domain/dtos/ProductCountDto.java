package com.kl.productshop.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ProductCountDto {

    @Expose
    private Long count;

    @Expose
    private List<ProductNameAndPriceDto> products;

    public ProductCountDto() {
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<ProductNameAndPriceDto> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductNameAndPriceDto> products) {
        this.products = products;
    }
}
