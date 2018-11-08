package Problems;

import entities.Project;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

public class P09_Find_Latest_10_Projects {
    private final EntityManager em;

    public P09_Find_Latest_10_Projects(EntityManager em) {
        this.em = em;
    }

    public void find_Latest_10_Projects() {

        List<Project> projects = em.createQuery("SELECT p FROM Project AS p ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();

        projects.stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> {
                    System.out.println("Project name: " + p.getName());
                    System.out.println("\tProject Description: " + p.getDescription());
                    System.out.println("\tProject Start Date: " + p.getStartDate());
                    System.out.println("\tProject End Date: " + p.getEndDate());
                });
    }
}
