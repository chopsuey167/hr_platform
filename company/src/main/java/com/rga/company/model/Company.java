package com.rga.company.model;

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
public class Company {

  @Id
  @SequenceGenerator(
      name = "company_id_sequence",
      sequenceName = "company_id_sequence"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "company_id_sequence"
  )
  private Integer id;
  private String name;
  private String businessName;
  private Long taxIdentificationNumber;
  private String address;
  private Boolean active;

}
