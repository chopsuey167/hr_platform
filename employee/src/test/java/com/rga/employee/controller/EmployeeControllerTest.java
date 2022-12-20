package com.rga.employee.controller;

import com.rga.employee.dto.EmployeeDto;
import com.rga.employee.model.Employee;
import com.rga.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;
    @Mock
    private EmployeeService employeeService;

    @Test
    void createNewEmployee_InputEmployeeData_EmployeeCreated() {
        // given
        EmployeeDto employeeDto = EmployeeDto.builder()
                .firstname("Ricardo")
                .lastname("gutierrez")
                .active(true)
                .build();
        Employee employee = Employee.builder()
                .firstname("Ricardo")
                .lastname("gutierrez")
                .active(true)
                .build();

        // when
        when(employeeService.create(employeeDto)).thenReturn(employee);
        Employee createdEmployee = employeeController.createNewEmployee(employeeDto).getBody();

        // then
        assertEquals(createdEmployee, employee);
        verify(employeeService, times(1)).create(any());
    }
}