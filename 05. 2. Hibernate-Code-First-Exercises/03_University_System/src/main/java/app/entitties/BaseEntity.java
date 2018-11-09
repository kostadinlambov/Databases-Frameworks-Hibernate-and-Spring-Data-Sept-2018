package app.entitties;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
