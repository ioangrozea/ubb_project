package com.internshipfinder.demo.controller;

import com.internshipfinder.demo.business.dto.CompanyDTO;
import com.internshipfinder.demo.business.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<Set<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(this.companyService.getCompanies());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<CompanyDTO> findCompanyById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.companyService.getCompanyById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity createCompany(@RequestBody CompanyDTO companyDTO) {
        this.companyService.createCompany(companyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        try {
            return ResponseEntity.ok(this.companyService.updateCompany(id, companyDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity deleteCompany(@PathVariable Long id) {
        this.companyService.deleteCompany(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/validate/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity validateCompany(@PathVariable Long id) throws Exception {
        this.companyService.validateCompany(id);
        return ResponseEntity.ok().build();
    }
}
