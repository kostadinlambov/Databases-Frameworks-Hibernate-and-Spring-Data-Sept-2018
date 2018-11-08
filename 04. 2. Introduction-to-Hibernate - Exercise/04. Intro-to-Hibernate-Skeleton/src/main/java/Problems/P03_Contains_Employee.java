package Problems;

import entities.Employee;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class P03_Contains_Employee {
    private final EntityManager em;

    public P03_Contains_Employee(EntityManager em) {
        this.em = em;
    }

    public void containsEmployee() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fullName = reader.readLine();

        List<Employee> resultList =
                em.createQuery("SELECT e FROM Employee AS e " +
                        "WHERE concat(e.firstName, ' ', e.lastName) =  :name", Employee.class)
                        .setParameter("name", fullName)
                        .getResultList();

        if(resultList.size() > 0){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}
