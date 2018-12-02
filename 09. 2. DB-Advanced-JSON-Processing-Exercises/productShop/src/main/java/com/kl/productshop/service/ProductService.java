package com.kl.productshop.service;


import com.kl.productshop.domain.dtos.ProductInRangeDto;
import com.kl.productshop.domain.dtos.ProductSeedDto;
import com.kl.productshop.domain.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts(ProductSeedDto[] productSeedDtos);

    List<ProductInRangeDto> productsInRange(BigDecimal more, BigDecimal less);

    List<Product> successfullySoldProducts();
}
