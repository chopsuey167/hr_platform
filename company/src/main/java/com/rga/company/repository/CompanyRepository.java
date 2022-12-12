package com.rga.company.repository;

import com.rga.company.model.Company;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

  Optional<Company> findByTaxIdentificationNumber(Long taxIdentificationNumber);

}
