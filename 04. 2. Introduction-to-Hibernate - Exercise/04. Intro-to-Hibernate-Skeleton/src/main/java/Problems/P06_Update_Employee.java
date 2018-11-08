package Problems;

import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P06_Update_Employee  {
    private final EntityManager em;

    public P06_Update_Employee(EntityManager em) {
        this.em = em;
    }

    public void updateEmployee() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputLastName = reader.readLine();

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");

        Employee employee = em.createQuery(
                "SELECT e FROM Employee AS e " +
                        "WHERE e.lastName = :name", Employee.class)
                .setParameter("name", inputLastName)
                .setMaxResults(1)
                .getSingleResult();

        Town town = em.createQuery(
                "SELECT t FROM Town AS t WHERE t.name = 'Sofia'", Town.class)
                .getSingleResult();

        newAddress.setTown(town);

        em.persist(newAddress);

        em.detach(employee);
        employee.setAddress(newAddress);
        em.merge(employee);
    }
}
