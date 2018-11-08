package Problems;

import javax.persistence.EntityManager;
import java.util.List;

public class P13_Employees_Maximum_Salaries {
    private final EntityManager em;

    public P13_Employees_Maximum_Salaries(EntityManager em) {
        this.em = em;
    }

    public void employeesMaximumSalaries(){
        List<Object[]> resultList = em.createQuery(
                "SELECT e.department.name , MAX(e.salary) " +
                "FROM Employee AS e " +
                "GROUP BY e.department.id " +
                "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                .getResultList();

        resultList.forEach(e -> System.out.println(e[0] + " - " + e[1]));
    }
}
