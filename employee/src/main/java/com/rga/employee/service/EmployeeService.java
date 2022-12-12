package com.rga.employee.service;

import com.rga.employee.dto.EmployeeDto;
import com.rga.employee.model.Employee;
import com.rga.employee.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public Employee create(EmployeeDto employeeDto) {

    if (employeeRepository.findByIdentificationNumber(employeeDto.getIdentificationNumber()).isPresent()) {
      return null;
    }

    Employee employee = Employee.builder()
        .firstname(employeeDto.getFirstname())
        .lastname(employeeDto.getLastname())
        .identificationNumber(employeeDto.getIdentificationNumber())
        .active(employeeDto.getActive())
        .build();

    return employeeRepository.save(employee);
  }

  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  public Employee update(Integer employeeId, EmployeeDto employeeDto) {
    Optional<Employee> employee = employeeRepository.findById(employeeId);

    if (!employee.isPresent()) {
      return null;
    }

    employee.get().setFirstname(employeeDto.getFirstname());
    employee.get().setLastname(employeeDto.getLastname());
    employee.get().setIdentificationNumber(employeeDto.getIdentificationNumber());
    employee.get().setActive(employeeDto.getActive());

    return employeeRepository.save(employee.get());
  }


}
