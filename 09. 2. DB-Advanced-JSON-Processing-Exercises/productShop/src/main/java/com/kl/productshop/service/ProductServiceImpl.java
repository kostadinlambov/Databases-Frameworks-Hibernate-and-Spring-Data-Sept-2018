package com.kl.productshop.service;

import com.kl.productshop.domain.dtos.ProductInRangeDto;
import com.kl.productshop.domain.dtos.ProductSeedDto;
import com.kl.productshop.domain.entities.Category;
import com.kl.productshop.domain.entities.Product;
import com.kl.productshop.domain.entities.User;
import com.kl.productshop.repository.CategoryRepository;
import com.kl.productshop.repository.ProductRepository;
import com.kl.productshop.repository.UserRepository;
import com.kl.productshop.util.validation.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        for (ProductSeedDto productSeedDto : productSeedDtos) {
            if (!this.validatorUtil.isValid(productSeedDto)) {
                this.validatorUtil.violations(productSeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
            }

            Product entity = this.modelMapper.map(productSeedDto, Product.class);
            entity.setSeller(this.getRandomUser());

            Random random = new Random();

            if (random.nextInt() % 13 != 0) {
                entity.setBuyer(this.getRandomUser());
            }

            entity.setCategories(this.getRandomCategories());
            this.productRepository.saveAndFlush(entity);
        }
    }

    @Override
    public List<ProductInRangeDto> productsInRange(BigDecimal more, BigDecimal less) {
        List<Product> products = this.productRepository.findAllByPriceBetweenAndBuyerOrderByPrice(more, less, null);

        List<ProductInRangeDto> productInRangeDtos = new ArrayList<>();

        for (Product productEntity : products) {
            ProductInRangeDto productInRangeDto = this.modelMapper.map(productEntity, ProductInRangeDto.class);
            productInRangeDto.setSeller(String.format("%s %s", productEntity.getSeller().getFirstName(),productEntity.getSeller().getLastName() ));

            productInRangeDtos.add(productInRangeDto);
        }

        return productInRangeDtos;
    }

    @Override
    public List<Product> successfullySoldProducts() {
        List<Product> products = this.productRepository.findAllByBuyerIsNotNull();
        System.out.println();
        return null;
    }

    private User getRandomUser() {
        Random random = new Random();
        List<User> allUsers = this.userRepository.findAll();
        int randomUserId = random.nextInt(allUsers.size() - 1) + 1;
        return allUsers.get(randomUserId);
    }

    private List<Category> getRandomCategories() {
        Random random = new Random();
        List<Category> allCategories = this.categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();

        int categoriesCount = random.nextInt(allCategories.size() - 1) + 1;

        for (int i = 0; i < categoriesCount; i++) {
            categories.add(this.categoryRepository.getOne(random.nextInt(allCategories.size() - 1) + 1));
        }

        return categories;
    }
}
