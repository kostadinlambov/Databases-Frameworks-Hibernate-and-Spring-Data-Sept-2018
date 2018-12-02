package com.kl.productshop.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserAndSoldProductsDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;

    @Expose
    private Integer age;

    @Expose
    private List<ProductCountDto> soldProducts;

    public UserAndSoldProductsDto() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<ProductCountDto> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(List<ProductCountDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
