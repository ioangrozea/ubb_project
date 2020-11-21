package com.internshipfinder.demo.business.service;

import com.internshipfinder.demo.business.dto.CompanyDTO;
import com.internshipfinder.demo.business.dto.StudentDTO;
import com.internshipfinder.demo.business.dto.UserDTO;
import com.internshipfinder.demo.persistence.entity.Company;
import com.internshipfinder.demo.persistence.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public Set<CompanyDTO> getCompanies() {
        Iterable<Company> companies = this.companyRepository.findAll();
        return StreamSupport
                .stream(companies.spliterator(), false)
                .map(c -> this.modelMapper.map(c, CompanyDTO.class))
                .collect(Collectors.toSet());
    }

    public CompanyDTO getCompanyById(Long companyId) throws Exception {
        return modelMapper.map(
                this.companyRepository.findById(companyId).orElseThrow(Exception::new),
                CompanyDTO.class);
    }

    public boolean getCompanyByUsername(UserDTO userDTO) throws Exception {
        CompanyDTO foundCompanyDTO = this.modelMapper.map(this.companyRepository.findCompanyByUsername(userDTO.getUsername())
                .orElseThrow(Exception::new), CompanyDTO.class);
        if (this.passwordEncoder.matches(foundCompanyDTO.getPassword(), userDTO.getPassword())) {
            throw new Exception();
        }
        return true;
    }

    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        companyDTO.setPassword(passwordEncoder.encode(companyDTO.getPassword()));
        return this.modelMapper.map(
                this.companyRepository.save(modelMapper.map(companyDTO, Company.class)),
                CompanyDTO.class);
    }

    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) throws Exception {
        Company company = this.companyRepository.findById(id).orElseThrow(Exception::new);

        companyDTO.setId(id);
        companyDTO.setPassword(passwordEncoder.encode(companyDTO.getPassword()));
        return this.modelMapper.map(
                this.companyRepository.save(modelMapper.map(companyDTO, Company.class)),
                CompanyDTO.class);
    }

    public void deleteCompany(Long companyId) {
        this.companyRepository.deleteById(companyId);
    }
}
