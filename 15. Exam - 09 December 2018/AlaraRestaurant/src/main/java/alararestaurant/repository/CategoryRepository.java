package alararestaurant.repository;

import alararestaurant.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);


    @Query(value = "" +
            "SELECT c FROM Category AS c " +
            "JOIN c.items AS i " +
            "GROUP BY c.id " +
            "ORDER BY SIZE(c.items) DESC, SUM(i.price) DESC")
    List<Category> categoriesByCountOfItems();

}
