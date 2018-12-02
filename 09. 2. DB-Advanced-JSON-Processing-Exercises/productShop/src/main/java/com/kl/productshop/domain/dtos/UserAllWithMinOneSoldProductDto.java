package com.kl.productshop.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserAllWithMinOneSoldProductDto {
    @Expose
    private List<UserAndSoldProductsDto> users;

    public UserAllWithMinOneSoldProductDto() {
    }

    public List<UserAndSoldProductsDto> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserAndSoldProductsDto> users) {
        this.users = users;
    }
}
