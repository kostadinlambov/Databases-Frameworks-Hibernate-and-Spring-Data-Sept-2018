package com.kl.modelmapperlab.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "adresses")
public class Address extends BaseEntity{
    private String name;
    private Set<Employee> employees;

    public Address() {
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }


}
