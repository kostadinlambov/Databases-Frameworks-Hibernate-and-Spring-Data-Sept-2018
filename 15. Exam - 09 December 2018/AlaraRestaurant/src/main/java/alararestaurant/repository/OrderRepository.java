package alararestaurant.repository;

import alararestaurant.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT o FROM Order AS o " +
            "JOIN o.employee AS e " +
            "JOIN e.position AS p " +
            "WHERE p.name = 'Burger Flipper' " +
            "ORDER BY e.name, o.id")
    List<Order> ordersByBurgerFlippers();
}
