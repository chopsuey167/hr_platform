package com.rga.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

  private String firstname;
  private String lastname;
  private Long identificationNumber;
  private Boolean active;
}
