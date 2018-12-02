package bookshopSystem.services;

import bookshopSystem.domain.entities.Book;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface BookService {
    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBookBefore();

    List<Object[]>  getAllAuthorsOrOrderByBooksCount();

    List<Book> findAllByAuthorFirstNameAndAuthorLastName(String firstName, String lastName);
}
