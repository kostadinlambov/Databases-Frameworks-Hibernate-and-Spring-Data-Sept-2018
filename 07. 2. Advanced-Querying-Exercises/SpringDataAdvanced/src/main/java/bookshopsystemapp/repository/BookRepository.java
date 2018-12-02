package bookshopsystemapp.repository;

import bookshopsystemapp.domain.entities.AgeRestriction;
import bookshopsystemapp.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByCopiesLessThan(Integer integer);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerThan, BigDecimal higherThan);

    @Query(value = "SELECT b FROM bookshopsystemapp.domain.entities.Book AS b WHERE b.releaseDate < :dateBefore OR b.releaseDate > :dateAfter")
    List<Book> findAllByReleaseDateNotIn(@Param("dateBefore") LocalDate dateBeforeYear, @Param("dateAfter") LocalDate dateAfterYear);

    List<Book> findAllByTitleLike(String str);

    List<Book> findAllByAuthorLastNameStartingWithOrderByAuthorLastName(String str);

    @Query(value =
            "SELECT b FROM bookshopsystemapp.domain.entities.Book AS b " +
            "WHERE LENGTH(b.title) >= :number")
    List<Book> findBookByTitleLength(@Param("number") Integer titleLength);

    @Query(value =
            "SELECT CONCAT(a.first_name,' ', a.last_name,' - ',   SUM(b.copies)) FROM books AS b\n" +
                    "JOIN authors a on b.author_id = a.id\n" +
                    "GROUP BY a.id\n" +
                    "ORDER BY SUM(b.copies) DESC", nativeQuery = true)
    List<Object> getTotalNumberOfCopies();

    @Query(value =
            "SELECT b.title, b.editionType, b.ageRestriction, b.price " +
            "FROM bookshopsystemapp.domain.entities.Book AS b WHERE b.title = :title")
    List<Object[]> getReducedBook(@Param("title") String bookTitle);

    @Transactional
    @Modifying
    @Query(value =
            "UPDATE bookshopsystemapp.domain.entities.Book  " +
            "SET copies = copies + :copiesToAdd " +
            "WHERE releaseDate > :inputDate")
    int updateNumberOfCopies(@Param("copiesToAdd") Integer copies, @Param("inputDate") LocalDate releaseDate);

    @Transactional
    @Modifying
    @Query(value =
            "DELETE FROM bookshopsystemapp.domain.entities.Book  " +
                    "WHERE copies < :inputNumber")
    int deleteBookByCopies(int inputNumber);
}
