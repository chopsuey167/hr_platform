package com.rga.company.service;

import com.rga.company.dto.CompanyDto;
import com.rga.company.model.Company;
import com.rga.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company create(CompanyDto companyDto) {

        if (companyRepository.findByTaxIdentificationNumber(companyDto.getTaxIdentificationNumber()).isPresent()) {
            return null;
        }

        Company company = Company.builder()
                .name(companyDto.getName())
                .address(companyDto.getAddress())
                .businessName(companyDto.getBusinessName())
                .taxIdentificationNumber(companyDto.getTaxIdentificationNumber())
                .active(companyDto.getActive())
                .build();

        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company update(Integer companyId, CompanyDto companyDto) {
        Optional<Company> company = companyRepository.findById(companyId);

        if (!company.isPresent()) {
            return null;
        }

        company.get().setName(companyDto.getName());
        company.get().setBusinessName(companyDto.getBusinessName());
        company.get().setAddress(companyDto.getAddress());
        company.get().setTaxIdentificationNumber(companyDto.getTaxIdentificationNumber());
        company.get().setActive(companyDto.getActive());

        return companyRepository.save(company.get());
    }


}
