package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.AgeRestriction;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBookBefore();

    List<String> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<String> getGoldenBooksTitles(Integer numberOfCopies);

    List<String> getBooksByPrice(BigDecimal lowerThan, BigDecimal higherThan);

    List<String> findAllByReleaseDateNotIn (LocalDate dateBeforeYear, LocalDate dateAfterYear);

    List<String> findAllReleasedBefore(LocalDate date);

    List<String> booksSearch(String input);

    List<String> findTitlesByAuthor(String input);

    Integer booksCountByTitleLength(Integer titleLength);

    List<String> getBooksByAuthor();

    List<String> getReducedBook(String bookTitle);

   String increaseCopies(String inputDate, Integer copiesToAdd);

    String deleteBooks(int numberOfCopies);

    String getCountOfBooksByAuthor(String firstName, String lastName);
}
