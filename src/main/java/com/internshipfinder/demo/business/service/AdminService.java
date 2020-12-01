package com.internshipfinder.demo.business.service;

import com.internshipfinder.demo.business.dto.AdminDTO;
import com.internshipfinder.demo.business.dto.StudentDTO;
import com.internshipfinder.demo.business.dto.UserDTO;
import com.internshipfinder.demo.persistence.entity.Admin;
import com.internshipfinder.demo.persistence.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class AdminService {
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    public AdminDTO getAdminByUsername(UserDTO userDTO) throws Exception {
        AdminDTO foundAdminDTO = this.modelMapper.map(this.adminRepository.findAdminByUsername(userDTO.getUsername())
                .orElseThrow(Exception::new), AdminDTO.class);
        if (!this.passwordEncoder.matches(userDTO.getPassword(), foundAdminDTO.getPassword())) {
            throw new Exception();
        }
        return foundAdminDTO;
    }

    public Set<AdminDTO> getAdmins() {
        Iterable<Admin> admins = this.adminRepository.findAll();
        return StreamSupport
                .stream(admins.spliterator(), false)
                .map(c -> this.modelMapper.map(c, AdminDTO.class))
                .collect(Collectors.toSet());
    }

    public AdminDTO getAdminById(Long adminId) throws Exception {
        return modelMapper.map(
                this.adminRepository.findById(adminId).orElseThrow(Exception::new),
                AdminDTO.class);
    }

    public AdminDTO createAdmin(AdminDTO adminDTO) {
        adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        return this.modelMapper.map(
                this.adminRepository.save(modelMapper.map(adminDTO, Admin.class)),
                AdminDTO.class);
    }

    public AdminDTO updateAdmin(Long id, AdminDTO adminDTO) throws Exception {
        Admin admin = this.adminRepository.findById(id).orElseThrow(Exception::new);

        adminDTO.setId(id);
        adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        return this.modelMapper.map(
                this.adminRepository.save(modelMapper.map(adminDTO, Admin.class)),
                AdminDTO.class);
    }

    public void deleteAdmin(Long adminId) {
        this.adminRepository.deleteById(adminId);
    }


}
