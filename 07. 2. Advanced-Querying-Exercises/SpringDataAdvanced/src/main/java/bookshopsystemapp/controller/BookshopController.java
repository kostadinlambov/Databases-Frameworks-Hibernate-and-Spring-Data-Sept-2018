package bookshopsystemapp.controller;

import bookshopsystemapp.domain.entities.AgeRestriction;
import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import bookshopsystemapp.util.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class BookshopController implements CommandLineRunner {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    @Autowired
    public BookshopController( AuthorService authorService, CategoryService categoryService, BookService bookService, BufferedReader bufferedReader) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... strings) throws Exception {
        /*
         * Problem 1. Books Titles by Age Restriction
         */
        booksByAgeRestriction();

        /*
         * Problem 2. Golden Books
         */
//        getGoldenBooks();

        /*
         * Problem 3. Books by Price
         */
//        booksByPrice();

        /*
         * Problem 4. Not Released Books
         */
//        notReleasedBooks();

        /*
         * Problem 5. Books Released Before Date
         */
//        booksReleasedBeforeDate();

        /*
         * Problem 6. Authors Search
         */
//        authorsSearch();

        /*
         * Problem 7. Books Search
         */
//        booksSearch();

        /*
         * Problem 8. Book Titles Search
         */
//        booksTitleSearch();

        /*
         * Problem 9. Count Books
         */
//        booksCount();

        /*
         * Problem 10. Total Book Copies
         */
//        totalBookCopies();

        /*
         * Problem 11. Reduced Book
         */
//        reducedBook();

        /*
         * Problem 12. Increase Book Copies
         */
//        increaseBookCopies();

        /*
         * Problem 13. Remove Books
         */
//        removeBooks();

        /*
         * Problem 14. Stored Procedure
         */
//        storedProcedure();
    }

    private void storedProcedure() throws IOException {
     try{
         System.out.println("### Enter first name and last name: ");
         String[] names = bufferedReader.readLine().split("\\s+");
         String firstName = names[0];
         String lastName = names[1];

         System.out.println(this.bookService.getCountOfBooksByAuthor(firstName, lastName));
     }catch (Exception e){
         System.out.println("### Invalid input");
     }
    }

    private void removeBooks() throws IOException {
        System.out.println("### Enter number of copies: ");
        int numberOfCopies = Integer.parseInt(bufferedReader.readLine());
        System.out.println(this.bookService.deleteBooks(numberOfCopies));
    }

    private void increaseBookCopies() throws IOException {
        System.out.println("### Enter date: ");
        String inputDate = bufferedReader.readLine();

        System.out.println("### Enter number of copies: ");
        Integer copiesToAdd = Integer.parseInt(bufferedReader.readLine());

        System.out.println(this.bookService.increaseCopies(inputDate, copiesToAdd));


    }

    private void reducedBook() throws IOException {
        System.out.println("### Enter book title: ");
        String bookTitle = this.bufferedReader.readLine();

        List<String> resultList = this.bookService.getReducedBook(bookTitle);


        if (resultList.size() > 0) {
            resultList.forEach(System.out::println);
        } else {
            System.out.println("No matches in Database!");
        }
    }

    private void totalBookCopies() {
        this.bookService.getBooksByAuthor()
                .forEach(System.out::println);
    }

    private void booksCount() throws IOException {
        System.out.println("### Enter a number(Title Length): ");
        Integer titleLength = Integer.parseInt(this.bufferedReader.readLine());

        System.out.println(this.bookService.booksCountByTitleLength(titleLength));
    }

    private void booksTitleSearch() throws IOException {
        System.out.println("### Enter string to check the author name: ");
        String input = this.bufferedReader.readLine();

        List<String> resultList = this.bookService.findTitlesByAuthor(input);

        if (resultList.size() > 0) {
            resultList.forEach(System.out::println);
        } else {
            System.out.println("No matches in Database!");
        }
    }

    private void booksSearch() throws IOException {
        System.out.println("### Enter string to check the book title: ");
        String input = this.bufferedReader.readLine();
        String queryParam = "%" + input + "%";
        List<String> resultList = this.bookService.booksSearch(queryParam);

        if (resultList.size() > 0) {
            resultList.forEach(System.out::println);
        } else {
            System.out.println("No matches in Database!");
        }
    }

    private void authorsSearch() throws IOException {
        System.out.println("### Enter string to check the first name: ");
        String input = this.bufferedReader.readLine();
        List<String> resultList = this.authorService.findAllByFirstNameEndingWith(input);

        if (resultList.size() > 0) {
            resultList.forEach(System.out::println);
        } else {
            System.out.println("No matches in Database!");
        }
    }

    private void booksReleasedBeforeDate() throws IOException {
        System.out.println("### Enter release date: ");
        String inputDateStr = bufferedReader.readLine();
        LocalDate date = LocalDate.parse(inputDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println();

        List<String> resultList = this.bookService.findAllReleasedBefore(date);

        if (resultList.size() > 0) {
            resultList.forEach(System.out::println);
        } else {
            System.out.println("No matches in Database!");
        }
    }

    private void notReleasedBooks() throws IOException {
        System.out.println("### Enter release year: ");
        Integer year = Integer.parseInt(bufferedReader.readLine());
        String yearBefore = String.format("%d-12-31", (year - 1));
        String yearAfter = String.format("%d-01-01", (year + 1));

        LocalDate beforeReleaseYear = LocalDate.parse(yearBefore);
        LocalDate afterReleaseYear = LocalDate.parse(yearAfter);

        List<String> resultList = this.bookService
                .findAllByReleaseDateNotIn(beforeReleaseYear, afterReleaseYear);

        if (resultList.size() > 0) {
            resultList.forEach(System.out::println);
        } else {
            System.out.println("No matches in Database!");
        }
    }

    private void booksByPrice() {
        BigDecimal lowerThan = new BigDecimal("5");
        BigDecimal higherThan = new BigDecimal("40");

        List<String> resultList = this.bookService.getBooksByPrice(lowerThan, higherThan);

        if (resultList.size() > 0) {
            resultList.forEach(System.out::println);
        } else {
            System.out.println("No matches in Database!");
        }
    }

    private void getGoldenBooks() {
        List<String> resultList = this.bookService
                .getGoldenBooksTitles(Constants.NUMBER_OF_COPIES);

        if (resultList.size() > 0) {
            resultList.forEach(System.out::println);
        } else {
            System.out.println("No matches in Database!");
        }
    }

    private void booksByAgeRestriction() throws IOException {
        System.out.println("### Enter age restriction: ");
        String input = this.bufferedReader.readLine();
        AgeRestriction ageRestriction = AgeRestriction.valueOf(input.toUpperCase());

        List<String> resultList = this.bookService.findBooksByAgeRestriction(ageRestriction);

        if (resultList.size() > 0) {
            resultList.forEach(System.out::println);
        } else {
            System.out.println("No matches in Database!");
        }
    }
}
