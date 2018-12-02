package com.kl.modelmapperlab.services;

import com.kl.modelmapperlab.domain.entities.Address;
import com.kl.modelmapperlab.domain.entities.Employee;
import com.kl.modelmapperlab.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper mapper;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }


    @Override
    public Employee  persist(String firstName, String lastName, LocalDate birthday, Address address, Boolean isOnHoliday, Employee manager) {
        Employee employee = new Employee();
        employee.setFirstName("Pesho");
        employee.setLastName("Peshev");
        employee.setBirthday(LocalDate.parse("2000-03-25"));
        employee.setSalary(new BigDecimal(25.3));
        employee.setAddress(address);
        employee.setOnHoliday(isOnHoliday);
        employee.setManager(manager);

        Employee savedEmployee = this.employeeRepository.saveAndFlush(employee);
        return savedEmployee;
    }
}
