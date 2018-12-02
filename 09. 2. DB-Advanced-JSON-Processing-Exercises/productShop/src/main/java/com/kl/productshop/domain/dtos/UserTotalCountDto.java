package com.kl.productshop.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserTotalCountDto {
    @Expose
    private String usersCount;
    @Expose
    private List<UserAllWithMinOneSoldProductDto> users;

    public UserTotalCountDto() {
    }

    public String getUsersCount() {
        return this.usersCount;
    }

    public void setUsersCount(String usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserAllWithMinOneSoldProductDto> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserAllWithMinOneSoldProductDto> users) {
        this.users = users;
    }
}
