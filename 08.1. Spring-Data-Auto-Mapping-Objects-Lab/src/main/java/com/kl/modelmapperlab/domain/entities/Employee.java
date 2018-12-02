package com.kl.modelmapperlab.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity{
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate birthday;
    private Address address;
    private Boolean isOnHoliday;
    private Employee manager;
    private Set<Employee> servants;

    public Employee() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "salary")
    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Column(name = "birthday")
    public LocalDate getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(name = "is_on_holiday")
    public Boolean getOnHoliday() {
        return this.isOnHoliday;
    }

    public void setOnHoliday(Boolean onHoliday) {
        isOnHoliday = onHoliday;
    }

    @ManyToOne
    public Employee getManager() {
        return this.manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @OneToMany(mappedBy = "manager")
    public Set<Employee> getServants() {
        return this.servants;
    }

    public void setServants(Set<Employee> servants) {
        this.servants = servants;
    }
}
