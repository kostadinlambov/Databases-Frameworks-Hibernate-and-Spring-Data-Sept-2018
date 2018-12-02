package com.kl.productshop.web.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kl.productshop.domain.dtos.*;
import com.kl.productshop.domain.entities.Product;
import com.kl.productshop.domain.entities.User;
import com.kl.productshop.io.FileIOUtil;
import com.kl.productshop.service.CategoryService;
import com.kl.productshop.service.ProductService;
import com.kl.productshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ProductShopController implements CommandLineRunner {
    private final static String PRODUCTS_FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\files\\output\\products-in-range.json";
    private final static String USERS_SOLD_PRODUCTS_FILE_PATH =System.getProperty("user.dir")+ "\\src\\\\main\\\\resources\\\\files\\\\output\\\\users-sold-products.json";
    private final static String CATEGORIES_BY_PRODUCTS_COUNT_FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\files\\output\\categories-by-products.json";

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final FileIOUtil fileIOUtil;
    private final Gson gson;

    @Autowired
    public ProductShopController(UserService userService, CategoryService categoryService, ProductService productService, FileIOUtil fileIOUtil, Gson gson) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.fileIOUtil = fileIOUtil;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        /* Query 1 - Products In Range*/
//        this.productsInRange();

        /* Query 2 - Successfully Sold Products*/
//        this.findSoldProducts();

        /* Query 3 - Categories By Products Count*/
//        this.getCategoriesAndOrderByProductCount();
    }

    private void getCategoriesAndOrderByProductCount() throws IOException {
        List<CategoryOrderedByProductsCountDto> categoriesAndOrderByProductsCountDtos = this.categoryService.getCategoriesAndOrderByProductsCount();
        String categoriesAndOrderByProductsCountJSON = this.gson.toJson(categoriesAndOrderByProductsCountDtos);

        System.out.println(categoriesAndOrderByProductsCountJSON);

        this.fileIOUtil.writeToFile(categoriesAndOrderByProductsCountJSON, CATEGORIES_BY_PRODUCTS_COUNT_FILE_PATH);
    }

    private void findSoldProducts() throws IOException {
        List<UserSoldProductsDto> userSoldProductsDtos = this.userService.usersSoldProducts();

        Type listType = new TypeToken<List<UserSoldProductsDto>>() {}.getType();
        List<UserSoldProductsDto> target = new LinkedList<UserSoldProductsDto>();
        target.addAll(userSoldProductsDtos);

        // List Parsed TO JSON
        String userSoldProductsJSON = this.gson.toJson(target, listType);

        // Parse FROM JSON to List
        List<String> target2 = this.gson.fromJson(userSoldProductsJSON, listType);

        System.out.println(userSoldProductsJSON);

        // Save JSON to File
        this.fileIOUtil.writeToFile(userSoldProductsJSON, USERS_SOLD_PRODUCTS_FILE_PATH);
    }

    private void productsInRange() throws IOException {
        List<ProductInRangeDto> productInRangeDtos = this.productService.productsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        String productsInRangeJSON = this.gson.toJson(productInRangeDtos);
        System.out.println(productsInRangeJSON);

        this.fileIOUtil.writeToFile(productsInRangeJSON, PRODUCTS_FILE_PATH);
    }
}



