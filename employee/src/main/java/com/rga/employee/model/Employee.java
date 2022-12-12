package com.rga.employee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

  @Id
  @SequenceGenerator(
      name = "employee_id_sequence",
      sequenceName = "employee_id_sequence"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "employee_id_sequence"
  )
  private Integer id;
  private String firstname;
  private String lastname;
  private Long identificationNumber;
  private Boolean active;

}
