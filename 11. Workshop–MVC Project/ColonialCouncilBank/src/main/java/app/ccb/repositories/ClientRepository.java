package app.ccb.repositories;

import app.ccb.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client ,Integer> {
   Optional<Client> findByFullName(String fullName);

   List<Client> findAllByFullName(String fullName);

   @Query(value = "" +
           "SELECT c FROM app.ccb.domain.entities.Client AS c " +
           "JOIN c.bankAccount AS b " +
           "ORDER BY SIZE(b.cards) DESC")
   List<Client> findClientWithTheMostCards();
}
