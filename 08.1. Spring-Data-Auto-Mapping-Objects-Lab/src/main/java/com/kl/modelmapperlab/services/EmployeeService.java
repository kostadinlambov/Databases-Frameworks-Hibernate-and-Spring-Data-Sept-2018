package com.kl.modelmapperlab.services;

import com.kl.modelmapperlab.domain.entities.Address;
import com.kl.modelmapperlab.domain.entities.Employee;

import java.time.LocalDate;

public interface EmployeeService {
    Employee persist(String firstName, String lastName, LocalDate birthday, Address address,Boolean isOnHoliday, Employee manager);
}
