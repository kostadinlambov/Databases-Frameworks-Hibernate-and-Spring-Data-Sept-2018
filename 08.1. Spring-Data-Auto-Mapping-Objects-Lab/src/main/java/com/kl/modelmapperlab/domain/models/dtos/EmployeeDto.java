package com.kl.modelmapperlab.domain.models.dtos;

import com.kl.modelmapperlab.domain.entities.BaseEntity;

import java.math.BigDecimal;

public class EmployeeDto {
    private String firstName;
    private String lastName;
    private BigDecimal salary;

    public EmployeeDto() {
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

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
