package bookshopsystemapp.util.entetiesSeed;



import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.repository.BookRepository;
import bookshopsystemapp.repository.CategoryRepository;
import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class EntitiesSeedExecutor {
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;


    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    public EntitiesSeedExecutor(AuthorRepository authorRepository, CategoryRepository categoryRepository, BookRepository bookRepository, AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @PostConstruct
    public void insertEntities() throws IOException {
        if(authorRepository.count() == 0){
            this.authorService.seedAuthors();
        }

        if(categoryRepository.count() == 0){
            this.categoryService.seedCategories();
        }

        if(bookRepository.count() == 0){
            this.bookService.seedBooks();
        }
    }
}
