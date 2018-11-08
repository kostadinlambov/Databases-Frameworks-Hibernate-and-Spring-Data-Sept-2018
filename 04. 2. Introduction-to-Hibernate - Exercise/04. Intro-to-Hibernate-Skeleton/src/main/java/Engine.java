import Problems.*;

import javax.persistence.EntityManager;
import java.io.IOException;

public class Engine implements Runnable {
    private final EntityManager em;

    public Engine(EntityManager em) {
        this.em = em;
    }

    @Override
    public void run() {
        em.getTransaction().begin();
        try {
            //Problem 2: Remove Objects
            P02_Remove_Objects p02 = new P02_Remove_Objects(em);
            p02.removeObjects();

            //Problem 3: Contains Employee
//            P03_Contains_Employee p03 = new P03_Contains_Employee(em);
//            p03.containsEmployee();

            //Problem 4. Employees with Salary Over 50000
//            P04_Employees_with_Salary_over_50000 p04 = new P04_Employees_with_Salary_over_50000(em);
//            p04.findEmployeesWithSalaryOver50000();

            //Problem 5. Employees from Department
//            P05_Employees_from_Department p05 = new P05_Employees_from_Department(em);
//            p05.employeesFromDepartmnet();

            //Problem 6. Adding a New Address and Updating Employee
//            P06_Update_Employee p06 = new P06_Update_Employee(em);
//            p06.updateEmployee();

            //Problem 7. Addresses with Employee Count
//            P07_Addresses_with_Employee_Count p07 = new P07_Addresses_with_Employee_Count(em);
//            p07.addressesWithEmployeesCount();

            //Problem 8. Get Employee with Project
//            P08_Get_Employee_with_Project p08 = new P08_Get_Employee_with_Project(em);
//            p08.employeeWithProjects();

            //Problem 9. Find Latest 10 Projects
//            P09_Find_Latest_10_Projects p09 = new P09_Find_Latest_10_Projects(em);
//            p09.find_Latest_10_Projects();

            //Problem 10. Increase Salaries
//            P10_Increase_Salaries p10 = new P10_Increase_Salaries(em);
//            p10.increaseSalaries();
//
            //Problem 11. Remove Towns
//            P11_Remove_Towns p11 = new P11_Remove_Towns(em);
//            p11.removeTowns();

            //Problem 12. Find Employees by First Name
//            P12_Find_Employees_by_First_Name p12 = new P12_Find_Employees_by_First_Name(em);
//            p12.findEmployeeByFirstName();

            //Problem 13. Employees Maximum Salaries
//            P13_Employees_Maximum_Salaries p13 = new P13_Employees_Maximum_Salaries(em);
//            p13.employeesMaximumSalaries();

        } catch (Exception e) {
            e.printStackTrace();
        }

        em.getTransaction().commit();
        em.close();
    }
}
