package com.kl.productshop.service;

import com.kl.productshop.domain.dtos.UserSeedDto;
import com.kl.productshop.domain.dtos.UserSoldProductsDto;

import java.util.List;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);

    List<UserSoldProductsDto> usersSoldProducts();
}
