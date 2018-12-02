package com.kl.productshop.web.controllers;

import com.kl.productshop.domain.dtos.seed_dtos.CategoryImportRootDto;
import com.kl.productshop.domain.dtos.seed_dtos.ProductImportRootDto;
import com.kl.productshop.domain.dtos.seed_dtos.UserImportRootDto;
import com.kl.productshop.io.xmlParser.XmlParser;
import com.kl.productshop.service.CategoryService;
import com.kl.productshop.service.ProductService;
import com.kl.productshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;


@Controller
public class ImportController {
    private final static String USER_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\users.xml";
    private final static String CATEGORIES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\categories.xml";
    private final static String PRODUCTS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\products.xml";

    private final XmlParser xmlParser;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;


    @Autowired
    public ImportController(XmlParser xmlParser, CategoryService categoryService, UserService userService, ProductService productService) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
    }

    public String importUsers() throws JAXBException, FileNotFoundException {
        UserImportRootDto userImportRootDto = this.xmlParser.parseXml(UserImportRootDto.class, USER_FILE_PATH);
        this.userService.seedUsersXml(userImportRootDto);
        return "Imported Users";
    }

    public String importCategories() throws JAXBException, FileNotFoundException {
        CategoryImportRootDto categoryImportRootDto = this.xmlParser.parseXml(CategoryImportRootDto.class, CATEGORIES_FILE_PATH);
        this.categoryService.seedCategoriesXml(categoryImportRootDto);
        return "Imported Categories";
    }

    public String importProducts() throws JAXBException, FileNotFoundException {
        ProductImportRootDto productImportRootDto = this.xmlParser.parseXml(ProductImportRootDto.class, PRODUCTS_FILE_PATH);
        this.productService.seedProductsXml(productImportRootDto);
        return "Imported Products";
    }
}
