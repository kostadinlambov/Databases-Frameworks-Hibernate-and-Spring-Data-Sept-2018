package Problems;

import entities.Employee;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class P12_Find_Employees_by_First_Name {
    private final EntityManager em;

    public P12_Find_Employees_by_First_Name(EntityManager em) {
        this.em = em;
    }

    public void findEmployeeByFirstName() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String pattern = reader.readLine() + "%";

        List<Employee> employeeList = em.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.firstName LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern)
                .getResultList();

        employeeList.forEach(e -> System.out.printf("%s %s - %s - ($%.2f)\n",
                e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));
    }
}
