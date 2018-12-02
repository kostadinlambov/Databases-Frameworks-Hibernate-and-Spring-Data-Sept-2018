package com.kl.modelmapperlab.repositories;

import com.kl.modelmapperlab.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
