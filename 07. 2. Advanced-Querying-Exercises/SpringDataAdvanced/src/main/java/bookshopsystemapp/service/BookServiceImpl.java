package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.*;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.repository.BookRepository;
import bookshopsystemapp.repository.CategoryRepository;
import bookshopsystemapp.util.constants.Constants;
import bookshopsystemapp.util.io.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileUtil fileUtil, EntityManager entityManager) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.entityManager = entityManager;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] booksFileContent = this.fileUtil.getFileContent(Constants.BOOKS_FILE_PATH);
        for (String line : booksFileContent) {
            String[] lineParams = line.split("\\s+");

            Book book = new Book();
            book.setAuthor(this.getRandomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(lineParams[0])];
            book.setEditionType(editionType);

            LocalDate releaseDate = LocalDate.parse(lineParams[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);

            int copies = Integer.parseInt(lineParams[2]);
            book.setCopies(copies);

            BigDecimal price = new BigDecimal(lineParams[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineParams[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder title = new StringBuilder();
            for (int i = 5; i < lineParams.length; i++) {
                title.append(lineParams[i]).append(" ");
            }

            book.setTitle(title.toString().trim());

            Set<Category> categories = this.getRandomCategories();
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        }
    }

    @Override
    public List<String> getAllBooksTitlesAfter() {
        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31"));

        return books.stream().map(b -> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public Set<String> getAllAuthorsWithBookBefore() {
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));

        return books.stream().map(b -> String.format("%s %s", b.getAuthor().getFirstName(), b.getAuthor().getLastName())).collect(Collectors.toSet());
    }


    @Override
    public List<String> findBooksByAgeRestriction(AgeRestriction ageRestriction) {

        List<Book> allByAgeRestriction = this.bookRepository.findAllByAgeRestriction(ageRestriction);
        return allByAgeRestriction.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> getGoldenBooksTitles(Integer numberOfCopies) {
        List<Book> allByCopiesLessThan = this.bookRepository.findAllByCopiesLessThan(5000);
        return allByCopiesLessThan.stream()
                .map(b -> String.format("%s - %d copies", b.getTitle(), b.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksByPrice(BigDecimal lowerThan, BigDecimal higherThan) {
        List<Book> books = this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lowerThan, higherThan);

        return books.stream().map(b -> String.format("%s - $%s", b.getTitle(), b.getPrice())).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByReleaseDateNotIn(LocalDate dateBeforeYear, LocalDate dateAfterYear) {
        List<Book> allByReleaseDateNotIn = this.bookRepository.findAllByReleaseDateNotIn(dateBeforeYear, dateAfterYear);

        return allByReleaseDateNotIn.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllReleasedBefore(LocalDate date) {
        List<Book> allByReleaseDateBefore = this.bookRepository.findAllByReleaseDateBefore(date);

        return allByReleaseDateBefore
                .stream()
                .map(b -> String.format("Title: %s, Edition Type %s, Price: $%s", b.getTitle(), b.getEditionType().name(), b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> booksSearch(String input) {
        List<Book> allByTitleLike = this.bookRepository.findAllByTitleLike(input);
        return allByTitleLike.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findTitlesByAuthor(String input) {
        List<Book> allByAuthorLastNameStartingWith = this.bookRepository.findAllByAuthorLastNameStartingWithOrderByAuthorLastName(input);

        return allByAuthorLastNameStartingWith
                .stream()
                .map(b -> String.format("%s (%s %s)", b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer booksCountByTitleLength(Integer titleLength) {
        List<Book> bookByTitleLength = this.bookRepository.findBookByTitleLength(titleLength);
        return bookByTitleLength.size();
    }

    @Override
    public List<String> getBooksByAuthor() {
        List<Object> totalNumberOfCopies = this.bookRepository.getTotalNumberOfCopies();
        return totalNumberOfCopies.stream().map(Object::toString).collect(Collectors.toList());
    }

    @Override
    public List<String> getReducedBook(String bookTitle) {
        List<Object[]> reducedBook = this.bookRepository.getReducedBook(bookTitle);

        return reducedBook.stream().map(b -> String.format("%s %s %s %s",
                b[0], b[1], b[2], b[3])).collect(Collectors.toList());

    }

    @Override
    public String increaseCopies(String inputDate, Integer copiesToAdd) {
        try {
            LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd MMM yyyy"));
            int numberOfChangedBooks = this.bookRepository.updateNumberOfCopies(copiesToAdd, date);
            int numberOfAddedCopies = numberOfChangedBooks * copiesToAdd;

            return String.format("%d books are released after %s, so total of %d book copies were added",
                    numberOfChangedBooks, inputDate, numberOfAddedCopies);

        } catch (Exception e) {
            return "## Invalid date format or number of copies format.";
        }
    }

    @Override
    public String deleteBooks(int numberOfCopies) {
        try {
            int numberOfDeletedBooks = this.bookRepository.deleteBookByCopies(numberOfCopies);

            return String.format("%d books were deleted",
                    numberOfDeletedBooks);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String getCountOfBooksByAuthor(String firstName, String lastName) {
     // Stored Procedure
     /*
     * USE bookshop_system_db;
       DROP PROCEDURE IF EXISTS usp_get_amount_of_books_by_author;

        DELIMITER $$

        CREATE PROCEDURE usp_get_amount_of_books_by_author(first_name VARCHAR(45), last_name VARCHAR(45))
        BEGIN
            SELECT COUNT(b.id) FROM books AS b
            JOIN authors AS a
            ON b.author_id = a.id
            WHERE a.first_name = first_name AND a.last_name = last_name
            GROUP BY a.first_name, a.last_name;
        END $$

        DELIMITER ;

        CALL usp_get_amount_of_books_by_author('Amanda', 'Rice');
     */

        try{
            StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_get_amount_of_books_by_author");

            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

            query.setParameter(1, firstName);
            query.setParameter(2, lastName);

            query.execute();

            BigInteger numberOfBooks = (BigInteger)query.getSingleResult();
            return String.format("%s %s has written %d books", firstName, lastName, numberOfBooks);
        }catch (Exception e){
            return String.format("%s %s has not written any books yet", firstName, lastName);
        }
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.authorRepository.count() - 1)) + 1;

        return this.authorRepository.findById(randomId).orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new LinkedHashSet<>();

        Random random = new Random();
        int length = random.nextInt(5);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();

            categories.add(category);
        }

        return categories;
    }

    private Category getRandomCategory() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;

        return this.categoryRepository.findById(randomId).orElse(null);
    }
}
