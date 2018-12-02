package com.kl.productshop.service;


import com.kl.productshop.domain.dtos.query1_dtos.ProductExportRootDto;
import com.kl.productshop.domain.dtos.seed_dtos.ProductImportRootDto;
import com.kl.productshop.domain.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {


    List<Product> successfullySoldProducts();

    String seedProductsXml(ProductImportRootDto productImportRootDto);

    ProductExportRootDto productsInRangeXml(BigDecimal more, BigDecimal less);
}
