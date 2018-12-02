package bookshopSystem.controllers;

import bookshopSystem.domain.entities.Book;
import bookshopSystem.services.AuthorService;
import bookshopSystem.services.BookService;
import bookshopSystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.sound.midi.Soundbank;
import java.util.List;

@Controller
public class BooksController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public BooksController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {

        //########################
        // Write Queries
        //########################

        // Problem 1:
        System.out.println("###### Problem 1: ");
        getBooksTitlesAfterYear2000();
        System.out.println();

        // Problem 2:
        System.out.println("###### Problem 2: ");
        getAuthorsWithBookBefore1990();
        System.out.println();

        // Problem 3:
        System.out.println("###### Problem 3: ");
        getAuthorsWithCountOfTheirBooks();
        System.out.println();

        // Problem 4:
        System.out.println("###### Problem 4: ");
        String firstName = "George";
        String lastName = "Powell";
        getAllBooksFromAuthor(firstName, lastName);
    }

    private void getAllBooksFromAuthor(String firstName, String lastName) {
        List<Book> books = this.bookService.findAllByAuthorFirstNameAndAuthorLastName(firstName, lastName);

        books.forEach(b ->
                System.out.printf("Author: %s %s, Title: %s, Release Date: %s, Copies: %d:%n",
                        b.getAuthor().getFirstName(), b.getAuthor().getLastName(),
                        b.getTitle(), b.getReleaseDate(), b.getCopies()));
    }

    private void getAuthorsWithCountOfTheirBooks() {
        this.bookService.getAllAuthorsOrOrderByBooksCount()
                .forEach(a -> System.out.println(a[0] + " " + a[1] + " - " + a[2]));
    }

    private void getAuthorsWithBookBefore1990() {
        this.bookService.getAllAuthorsWithBookBefore().forEach(System.out::println);
    }

    private void getBooksTitlesAfterYear2000() {
        this.bookService.getAllBooksTitlesAfter().forEach(System.out::println);
    }
}
