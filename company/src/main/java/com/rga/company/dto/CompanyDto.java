package com.rga.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private String name;
    private String businessName;
    private Long taxIdentificationNumber;
    private String address;
    private Boolean active;
}
