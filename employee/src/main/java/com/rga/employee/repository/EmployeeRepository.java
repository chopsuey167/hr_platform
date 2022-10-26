package com.rga.employee.repository;

import com.rga.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Optional<Employee> findByIdentificationNumber(Long identificationNumber);
}
