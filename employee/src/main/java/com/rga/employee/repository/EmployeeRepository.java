package com.rga.employee.repository;

import com.rga.employee.model.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  Optional<Employee> findByIdentificationNumber(Long identificationNumber);
}
