package Problems;

import entities.Address;

import javax.persistence.EntityManager;
import java.util.List;

public class P07_Addresses_with_Employee_Count {
    private final EntityManager em;

    public P07_Addresses_with_Employee_Count(EntityManager em) {
        this.em = em;
    }

    public void addressesWithEmployeesCount(){
        List<Address> addressList = em.createQuery(
                "SELECT a FROM Address AS a ORDER BY a.employees.size DESC, a.town.id ASC", Address.class)
                .setMaxResults(10)
                .getResultList();

        addressList.forEach(a -> System.out.println(String.format("%s, %s - %d employees",
                a.getText(), a.getTown().getName(), a.getEmployees().size())));
    }
}
