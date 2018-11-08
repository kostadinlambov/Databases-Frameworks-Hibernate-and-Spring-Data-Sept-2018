package Problems;

import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class P10_Increase_Salaries {
    private final EntityManager em;

    public P10_Increase_Salaries(EntityManager em) {
        this.em = em;
    }

    public void increaseSalaries(){

        List<Employee> employees = em.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services') " +
                "ORDER BY e.id", Employee.class)
                .getResultList();

        employees.forEach(e ->{
                e.setSalary(e.getSalary().multiply(new BigDecimal(1.12)));
                System.out.printf("%s %s ($%.2f)\n", e.getFirstName(), e.getLastName(), e.getSalary());
        });
    }
}
