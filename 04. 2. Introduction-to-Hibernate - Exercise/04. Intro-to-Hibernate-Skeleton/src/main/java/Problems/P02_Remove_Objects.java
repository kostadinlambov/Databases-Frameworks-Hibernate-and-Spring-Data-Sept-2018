package Problems;

import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;

public class P02_Remove_Objects {

    private final EntityManager em;

    public P02_Remove_Objects(EntityManager em) {
        this.em = em;
    }

    public void removeObjects() {
        List<Town> townList =
                em.createQuery("SELECT t FROM Town AS t WHERE length(t.name) > 5", Town.class)
                        .getResultList();

        for (Town town : townList) {
            em.detach(town);
            String townsNameLowerCase = town.getName().toLowerCase();
            town.setName(townsNameLowerCase);
            em.merge(town);
        }
    }
}
