package com.kl.modelmapperlab.domain.models.dtos;

import com.kl.modelmapperlab.domain.entities.Employee;

import java.util.Set;

public class ManagerDto {
    private String firstName;
    private String lastName;
    private Employee manager;
    private Set<Employee> servants;

    public ManagerDto() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Employee getManager() {
        return this.manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Employee> getServants() {
        return this.servants;
    }

    public void setServants(Set<Employee> servants) {
        this.servants = servants;
    }
}
