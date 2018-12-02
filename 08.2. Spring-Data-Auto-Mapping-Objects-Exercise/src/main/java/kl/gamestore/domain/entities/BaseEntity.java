package kl.gamestore.domain.entities;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {
    private Long id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
