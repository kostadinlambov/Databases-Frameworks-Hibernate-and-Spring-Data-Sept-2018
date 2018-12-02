package com.kl.productshop.domain.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryOrderedByProductsCountDto {
    @Expose
    private String name;

    @Expose
    private Long productsCount;

    @Expose
    private Double averagePrice;

    @Expose
    private BigDecimal totalRevenue;

    public CategoryOrderedByProductsCountDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductsCount() {
        return this.productsCount;
    }

    public void setProductsCount(Long productsCount) {
        this.productsCount = productsCount;
    }

    public Double getAveragePrice() {
        return this.averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return this.totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
