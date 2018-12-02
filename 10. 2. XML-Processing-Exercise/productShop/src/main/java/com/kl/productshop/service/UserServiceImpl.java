package com.kl.productshop.service;

import com.kl.productshop.domain.dtos.seed_dtos.UserImportDto;
import com.kl.productshop.domain.dtos.seed_dtos.UserImportRootDto;
import com.kl.productshop.domain.entities.User;
import com.kl.productshop.repository.UserRepository;
import com.kl.productshop.util.validation.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

//    @Override
//    public List<UserSoldProductsDto> usersSoldProducts() {
//        List<User> allSellers = this.userRepository.getAllSellers();
//        List<UserSoldProductsDto> userSoldProductsDtos = new ArrayList<>();
//
//        for (User user : allSellers) {
//            UserSoldProductsDto userSoldProductsDto = modelMapper.map(user, UserSoldProductsDto.class);
//
//            userSoldProductsDtos.add(userSoldProductsDto);
//        }
//        System.out.println();
//        return userSoldProductsDtos;
//    }

    @Override
    public String seedUsersXml(UserImportRootDto userImportRootDto) {
        for (UserImportDto userSeedDto : userImportRootDto.getUserImportDtoList()) {

            if(!this.validatorUtil.isValid(userSeedDto)){
                this.validatorUtil.violations(userSeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage())
                        );
                continue;
            }

            User entity = this.modelMapper.map(userSeedDto, User.class);
            this.userRepository.saveAndFlush(entity);
        }
        return null;
    }

//    @Override
//    public void seedUsers(UserSeedDto[] userSeedDtos) {
//        for (UserSeedDto userSeedDto : userSeedDtos) {
//
//            if(!this.validatorUtil.isValid(userSeedDto)){
//                this.validatorUtil.violations(userSeedDto)
//                        .forEach(violation -> System.out.println(violation.getMessage())
//                );
//                continue;
//            }
//
//            User entity = this.modelMapper.map(userSeedDto, User.class);
//            this.userRepository.saveAndFlush(entity);
//        }
//    }
}
