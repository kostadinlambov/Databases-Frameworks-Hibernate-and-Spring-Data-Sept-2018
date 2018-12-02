package com.kl.productshop.web.controllers;

import com.kl.productshop.domain.dtos.query1_dtos.ProductExportRootDto;
import com.kl.productshop.io.xmlParser.XmlParser;
import com.kl.productshop.service.CategoryService;
import com.kl.productshop.service.ProductService;
import com.kl.productshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

@Controller
public class ExportController {

    private final static String PRODUCTS_FILE_PATH =  System.getProperty("user.dir")+"\\src\\main\\resources\\files\\output\\products-in-range.xml";
    private final static String USERS_SOLD_PRODUCTS_FILE_PATH =System.getProperty("user.dir")+ "\\src\\\\main\\\\resources\\\\files\\\\output\\\\users-sold-products.xml";
    private final static String CATEGORIES_BY_PRODUCTS_COUNT_FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\files\\output\\categories-by-products.xml";

    private final XmlParser xmlParser;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public ExportController(XmlParser xmlParser, CategoryService categoryService, UserService userService, ProductService productService) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
    }

    public String getOrderedCustomers() throws FileNotFoundException, JAXBException {
        ProductExportRootDto productExportRootDto = this.productService.productsInRangeXml(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        this.xmlParser.exportXml(productExportRootDto, ProductExportRootDto.class, PRODUCTS_FILE_PATH);
        return null;
    }
}
