package app.ccb.repositories;

import app.ccb.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee ,Integer> {
    Optional<Employee> findAllByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT e FROM app.ccb.domain.entities.Employee AS e " +
            "ORDER BY SIZE(e.clients) DESC, e.id")
    List<Employee> findAllEmployeesAndOrder();

}
