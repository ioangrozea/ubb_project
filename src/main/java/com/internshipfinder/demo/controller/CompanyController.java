package com.internshipfinder.demo.controller;

import com.internshipfinder.demo.business.dto.CompanyDTO;
import com.internshipfinder.demo.business.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<Set<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(this.companyService.getCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findCompanyById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.companyService.getCompanyById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity createCompany(@RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(this.companyService.createCompany(companyDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        try {
            return ResponseEntity.ok(this.companyService.updateCompany(id, companyDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompany(@PathVariable Long id) {
        this.companyService.deleteCompany(id);
        return ResponseEntity.ok().build();
    }
}
