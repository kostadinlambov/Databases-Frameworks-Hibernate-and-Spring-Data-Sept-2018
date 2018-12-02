package com.kl.cardealer.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {
    private String name;
    private Boolean isImporter;
    private List<Part> parts;

    public Supplier() {
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "is_importer")
    public Boolean getImporter() {
        return this.isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    public List<Part> getParts() {
        return this.parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
