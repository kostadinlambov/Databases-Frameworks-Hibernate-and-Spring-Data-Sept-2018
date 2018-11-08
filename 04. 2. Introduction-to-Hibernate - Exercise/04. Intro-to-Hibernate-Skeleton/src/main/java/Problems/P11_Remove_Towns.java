package Problems;

import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class P11_Remove_Towns {
    private final EntityManager em;

    public P11_Remove_Towns(EntityManager em) {
        this.em = em;
    }

    public void removeTowns() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String townName = reader.readLine();

        List<Employee> employees = em.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.address.town.name = :townName", Employee.class)
                .setParameter("townName", townName).getResultList();


        List<Address> addressList = em.createQuery("SELECT a FROM Address AS a " +
                "WHERE a.town.name = :townName", Address.class)
                .setParameter("townName", townName)
                .getResultList();

        List<Town> towns = em.createQuery("SELECT t FROM Town AS t " +
                "WHERE t.name = :townName", Town.class)
                .setParameter("townName", townName)
                .getResultList();


        int adressesCount = addressList.size();

        employees.forEach(e-> e.setAddress(null));
        addressList.forEach(em::remove);
        towns.forEach(em::remove);

        if(adressesCount == 1){
            System.out.println(String.format("%d address in %s deleted", adressesCount, townName));
        }else{
            System.out.println(String.format("%d addresses in %s deleted", adressesCount, townName));
        }
    }
}
