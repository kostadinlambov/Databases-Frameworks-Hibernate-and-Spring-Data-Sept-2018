package mostwanted.domain.entities;

import javax.persistence.*;

@Entity()
@Table(name = "districts")
public class District extends BaseEntity {
    private String name;
    private Town town;

    public District() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Town.class)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    public Town getTown() {
        return this.town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
