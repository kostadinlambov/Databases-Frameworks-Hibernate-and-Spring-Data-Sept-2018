package kl.gamestore.repositories;

import kl.gamestore.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders WHERE user_id = :userId AND game_id = :gameId"
            , nativeQuery = true)
    List<Order> findAllByUserIdAndGameId(@Param("userId") Long userId, @Param("gameId") Long gameId);

    @Query(value = "SELECT * FROM orders WHERE user_id = :userId"
            , nativeQuery = true)
    List<Order> findAllByUserId(@Param("userId") Long userId);
}
