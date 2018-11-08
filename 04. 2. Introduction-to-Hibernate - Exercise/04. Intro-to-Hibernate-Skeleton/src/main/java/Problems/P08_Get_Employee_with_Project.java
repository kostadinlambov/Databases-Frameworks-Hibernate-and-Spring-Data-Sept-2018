package Problems;

import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class P08_Get_Employee_with_Project {
    private final EntityManager em;

    public P08_Get_Employee_with_Project(EntityManager em) {
        this.em = em;
    }

    public void employeeWithProjects() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int id = Integer.parseInt(reader.readLine());

        Employee employee = em.createQuery("SELECT e FROM Employee AS e " +
                                                "WHERE e.id = :id", Employee.class)
                                                .setParameter("id", id)
                                                .getSingleResult();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s - %s",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle())).append(System.lineSeparator());

        employee.getProjects().stream().sorted(Comparator.comparing(Project::getName))
                .forEach(p -> sb.append(String.format("\t%s" ,p.getName())).append(System.lineSeparator()));

        System.out.println(sb.toString());
    }
}
