package com.rga.company.repository;

import com.rga.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

    Optional<Company> findByTaxIdentificationNumber(Long taxIdentificationNumber);

}
