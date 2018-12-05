package mostwanted.repository;

import mostwanted.domain.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepository  extends JpaRepository<Town, Integer> {
    Optional<Town> findByName(String townName);


    @Query(value = "SELECT t.name, count(r.id)\n" +
            "FROM racers AS r\n" +
            "       JOIN towns t on r.town_id = t.id\n" +
            "WHERE t.id IS NOT NULL\n" +
            "GROUP BY t.id\n" +
            "ORDER BY  count(r.id) DESC, t.name", nativeQuery = true)
    List<Object[]> findAllTownsWithRacers();
}
