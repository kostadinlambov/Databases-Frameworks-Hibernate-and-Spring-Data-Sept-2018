package com.kl.modelmapperlab.controllers;

import com.kl.modelmapperlab.domain.entities.Address;
import com.kl.modelmapperlab.domain.entities.Employee;
import com.kl.modelmapperlab.domain.models.dtos.EmployeeDto;
import com.kl.modelmapperlab.repositories.AddressRepository;
import com.kl.modelmapperlab.repositories.EmployeeRepository;
import com.kl.modelmapperlab.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@Transactional
public class EmployeeController implements CommandLineRunner {

    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    private AddressRepository addressRepository;

    public EmployeeController(ModelMapper modelMapper, EmployeeService employeeService, EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Address address = new Address();
        address.setName("Sofia");

        this.addressRepository.saveAndFlush(address);


        Employee employee = new Employee();

        employee.setFirstName("Pesho");
        employee.setLastName("Peshev");
        employee.setBirthday(LocalDate.parse("2000-03-25"));
        employee.setSalary(new BigDecimal(25.3));
        employee.setAddress(address);
        employee.setOnHoliday(true);
        this.employeeRepository.saveAndFlush(employee);

        Employee employee2 = new Employee();
        employee2.setFirstName("Gosho");
        employee2.setLastName("Goshev");
        employee2.setBirthday(LocalDate.parse("1986-03-25"));
        employee2.setSalary(new BigDecimal(25.3));
        employee2.setAddress(address);
        employee2.setOnHoliday(true);
        employee2.setManager(employee);
        this.employeeRepository.saveAndFlush(employee2);


        Employee employee3 = new Employee();
        employee3.setFirstName("Gosho2");
        employee3.setLastName("Goshev2");
        employee3.setBirthday(LocalDate.parse("1966-03-25"));
        employee3.setSalary(new BigDecimal(25.3));
        employee3.setAddress(address);
        employee3.setOnHoliday(true);
        employee3.setManager(employee);
        this.employeeRepository.saveAndFlush(employee3);


        Employee employee4 = new Employee();
        employee4.setFirstName("Gosho3");
        employee4.setLastName("Goshev3");
        employee4.setBirthday(LocalDate.parse("1945-03-25"));
        employee4.setSalary(new BigDecimal(25.3));
        employee4.setAddress(address);
        employee4.setOnHoliday(true);
        employee4.setManager(employee);
        this.employeeRepository.saveAndFlush(employee4);

        EmployeeDto employeeDto = this.modelMapper.map(employee, EmployeeDto.class);

        System.out.println(employeeDto.getFirstName());
        System.out.println(employeeDto.getLastName());
        System.out.printf("%.2f\n", employeeDto.getSalary());

    }
}
