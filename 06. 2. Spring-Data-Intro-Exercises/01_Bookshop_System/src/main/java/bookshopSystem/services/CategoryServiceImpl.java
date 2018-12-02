package bookshopSystem.services;

import bookshopSystem.domain.entities.Category;
import bookshopSystem.repositories.CategoryRepository;
import bookshopSystem.util.io.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final static String CATEGORIES_FILE_PATH = "C:\\Users\\valch\\Desktop\\Java DB Projects\\01. Java DB Fundamentals-September-2018\\02. Databases Frameworks - Hibernate & Spring Data - October 2018\\06. 2. Spring-Data-Intro-Exercises\\01_Bookshop_System\\src\\main\\resources\\files\\categories.txt";

    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        String[] categoriesFileContent = this.fileUtil.getFileContent(CATEGORIES_FILE_PATH);
        for (String line : categoriesFileContent) {
            Category category = new Category();
            category.setName(line);

            this.categoryRepository.saveAndFlush(category);
        }
    }
}
