package com.kl.productshop.util.entitiesSeed;

import com.google.gson.Gson;
import com.kl.productshop.domain.dtos.CategorySeedDto;
import com.kl.productshop.domain.dtos.ProductSeedDto;
import com.kl.productshop.domain.dtos.UserSeedDto;
import com.kl.productshop.io.FileIOUtil;
import com.kl.productshop.repository.CategoryRepository;
import com.kl.productshop.repository.ProductRepository;
import com.kl.productshop.repository.UserRepository;
import com.kl.productshop.service.CategoryService;
import com.kl.productshop.service.ProductService;
import com.kl.productshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class EntitiesSeedExecutor {
    private final static String USER_FILE_PATH = "files/users.json";
    private final static String CATEGORIES_FILE_PATH = "files/categories.json";
    private final static String PRODUCTS_FILE_PATH = "files/products.json";

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final FileIOUtil fileIOUtil;
    private final Gson gson;

    @Autowired
    public EntitiesSeedExecutor(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository, UserService userService, CategoryService categoryService, ProductService productService, FileIOUtil fileIOUtil, Gson gson) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.fileIOUtil = fileIOUtil;
        this.gson = gson;
    }

    @PostConstruct
    public void insertEntities() throws IOException {

        // User initialisation
        if (this.userRepository.count() == 0L) {
            String usersFileContent = this.fileIOUtil.readJson(USER_FILE_PATH);
            UserSeedDto[] userSeedDtos = this.gson.fromJson(usersFileContent, UserSeedDto[].class);
            this.userService.seedUsers(userSeedDtos);
        }

        // Category initialisation
        if (this.categoryRepository.count() == 0L) {
            String categoriesFileContent = this.fileIOUtil.readJson(CATEGORIES_FILE_PATH);
            CategorySeedDto[] categorySeedDtos = this.gson.fromJson(categoriesFileContent, CategorySeedDto[].class);
            this.categoryService.seedCategories(categorySeedDtos);
        }

        // Product initialisation
        if (this.productRepository.count() == 0L) {
            String productsFileContent = this.fileIOUtil.readJson(PRODUCTS_FILE_PATH);
            ProductSeedDto[] productSeedDtos = this.gson.fromJson(productsFileContent, ProductSeedDto[].class);
            this.productService.seedProducts(productSeedDtos);
        }
    }
}
