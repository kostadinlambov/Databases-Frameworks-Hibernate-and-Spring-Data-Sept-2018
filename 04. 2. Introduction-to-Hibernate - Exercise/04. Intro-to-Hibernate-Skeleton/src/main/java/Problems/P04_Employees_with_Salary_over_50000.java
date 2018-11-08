package Problems;

import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class P04_Employees_with_Salary_over_50000 {
    private final EntityManager em;

    public P04_Employees_with_Salary_over_50000(EntityManager em) {
        this.em = em;
    }

    public void findEmployeesWithSalaryOver50000() {
        List<Employee> resultList =
                em.createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.salary > 50000", Employee.class)
                        .getResultList();

        resultList.stream().forEach(e -> System.out.println(e.getFirstName()));
    }
}
