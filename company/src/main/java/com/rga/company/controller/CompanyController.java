package com.rga.company.controller;

import com.rga.company.dto.CompanyDto;
import com.rga.company.model.Company;
import com.rga.company.service.CompanyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("companies")
public class CompanyController {

  private final CompanyService companyService;

  @Autowired
  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @PostMapping
  public ResponseEntity<Company> createNewCompany(@RequestBody CompanyDto companyDto) {
    Company company = companyService.create(companyDto);

    if (company == null) {
      return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(company);
  }

  @GetMapping
  public ResponseEntity<List<Company>> getAllCompanies() {
    return ResponseEntity.ok(companyService.getAllCompanies());
  }

  @PatchMapping(path = "{companyId}")
  public ResponseEntity<Company> updateCompany(
      @PathVariable("companyId") Integer companyId,
      @RequestBody CompanyDto companyDto) {

    Company company = companyService.update(companyId, companyDto);

    if (company == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(company);
  }
}
