package com.kl.productshop.service;

import com.kl.productshop.domain.dtos.seed_dtos.UserImportRootDto;

import java.util.List;

public interface UserService {
//    void seedUsers(UserSeedDto[] userSeedDtos);


    String seedUsersXml(UserImportRootDto userImportRootDto);
}
