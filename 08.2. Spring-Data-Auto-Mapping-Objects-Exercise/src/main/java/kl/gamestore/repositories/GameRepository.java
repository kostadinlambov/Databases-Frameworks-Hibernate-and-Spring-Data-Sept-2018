package kl.gamestore.repositories;

import kl.gamestore.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByTitle(String title);

    @Query(value = "SELECT * FROM games WHERE user_id = :userId"
            , nativeQuery = true)
    List<Game> findAllByUserId(@Param("userId") Long userId);

}
