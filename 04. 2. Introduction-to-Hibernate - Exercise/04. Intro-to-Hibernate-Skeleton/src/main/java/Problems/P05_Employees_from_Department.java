package Problems;

import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class P05_Employees_from_Department  {
    private final EntityManager em;

    public P05_Employees_from_Department(EntityManager em) {
        this.em = em;
    }

    public void employeesFromDepartmnet(){
        List<Employee> resultList = em.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.department.name = 'Research and Development' " +
                "ORDER BY e.salary ASC, e.id ASC", Employee.class).getResultList();

        resultList.forEach(e -> {
            System.out.println(String.format("%s %s from %s - $%s",
                    e.getFirstName(), e.getLastName(),e.getDepartment().getName(), e.getSalary()));
        });
    }
}
