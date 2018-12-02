package com.kl.productshop.service;

import com.kl.productshop.domain.dtos.CategoryOrderedByProductsCountDto;
import com.kl.productshop.domain.dtos.CategorySeedDto;
import com.kl.productshop.domain.entities.Category;
import com.kl.productshop.repository.CategoryRepository;
import com.kl.productshop.util.validation.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
        for (CategorySeedDto categorySeedDto : categorySeedDtos) {
            if(!this.validatorUtil.isValid(categorySeedDto)){
                this.validatorUtil.violations(categorySeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
            }

            Category entity = this.modelMapper.map(categorySeedDto, Category.class);
            this.categoryRepository.saveAndFlush(entity);
        }

    }

    @Override
    public List<CategoryOrderedByProductsCountDto> getCategoriesAndOrderByProductsCount() {
        List<Object[]> categories = this.categoryRepository.categoriesByProductCount();

        List<CategoryOrderedByProductsCountDto> categoryOrderedByProductsCountDtos = new ArrayList<>();




        for (Object[] category : categories) {
//            CategoryOrderedByProductsCountDto categoryDto = modelMapper.map(category, CategoryOrderedByProductsCountDto.class);
            CategoryOrderedByProductsCountDto categoryDto = new CategoryOrderedByProductsCountDto();
            categoryDto.setName(category[0].toString());
            categoryDto.setProductsCount((Long)category[1]);
            categoryDto.setAveragePrice((Double) category[2]);
            categoryDto.setTotalRevenue((BigDecimal) category[3]);

            categoryOrderedByProductsCountDtos.add(categoryDto);
        }


        System.out.println();
        return categoryOrderedByProductsCountDtos;
    }
}
