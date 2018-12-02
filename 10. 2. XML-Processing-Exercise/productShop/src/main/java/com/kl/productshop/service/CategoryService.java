package com.kl.productshop.service;

import com.kl.productshop.domain.dtos.seed_dtos.CategoryImportRootDto;

import java.util.List;

public interface CategoryService {

    String seedCategoriesXml(CategoryImportRootDto categoryImportRootDto);
}
