package alararestaurant.service;

import alararestaurant.domain.entities.Category;
import alararestaurant.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String exportCategoriesByCountOfItems() {
        StringBuilder result = new StringBuilder();

        List<Category> categories = this.categoryRepository.categoriesByCountOfItems();

        categories.forEach(category -> {
            result.append(String.format("Category: %s", category.getName()))
                    .append(System.lineSeparator());

            category.getItems().forEach(item -> {
                result.append(String.format("---Item Name: %s", item.getName())).append(System.lineSeparator());
                result.append(String.format("---Item Price: %s", item.getPrice())).append(System.lineSeparator());
                result.append(System.lineSeparator());
            });
        });

        return result.toString().trim();
    }

}
