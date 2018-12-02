package com.kl.productshop.service;

import com.kl.productshop.domain.dtos.CategoryOrderedByProductsCountDto;
import com.kl.productshop.domain.dtos.CategorySeedDto;

import java.util.List;

public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDto);

    List<CategoryOrderedByProductsCountDto> getCategoriesAndOrderByProductsCount();
}
