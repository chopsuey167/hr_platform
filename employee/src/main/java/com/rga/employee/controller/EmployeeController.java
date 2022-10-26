package com.rga.employee.controller;

import com.rga.employee.dto.EmployeeDto;
import com.rga.employee.model.Employee;
import com.rga.employee.service.EmployeeService;
import com.rga.employee.dto.EmployeeDto;
import com.rga.employee.model.Employee;
import com.rga.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createNewEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.create(employeeDto);

        if (employee == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PatchMapping(path = "{employeeId}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable("employeeId") Integer employeeId,
            @RequestBody EmployeeDto employeeDto){

        Employee employee = employeeService.update(employeeId,employeeDto);

        if (employee == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employee);
    }
}
