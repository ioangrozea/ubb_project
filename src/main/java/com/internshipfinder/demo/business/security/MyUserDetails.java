package com.internshipfinder.demo.business.security;

import com.internshipfinder.demo.business.dto.UserDTO;
import com.internshipfinder.demo.business.dto.UserType;
import com.internshipfinder.demo.persistence.repository.AdminRepository;
import com.internshipfinder.demo.persistence.repository.CompanyRepository;
import com.internshipfinder.demo.persistence.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MyUserDetails {
    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public UserDetails loadUserByUsername(String username, UserType userType) throws Exception {
        UserDTO userDTO = null;
        switch (userType) {
            case ROLE_ADMIN:
                userDTO = this.modelMapper.map(this.adminRepository.findAdminByUsername(username).orElseThrow(Exception::new),
                        UserDTO.class);
                break;
            case ROLE_COMPANY:
                userDTO = this.modelMapper.map(this.companyRepository.findCompanyByUsername(username).orElseThrow(Exception::new),
                        UserDTO.class);
                break;
            case ROLE_STUDENT:
                userDTO = this.modelMapper.map(this.studentRepository.findStudentByUsername(username).orElseThrow(Exception::new),
                        UserDTO.class);
                break;
        }

        if (userDTO == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        List<UserType> userTypeList = new ArrayList<>();
        userTypeList.add(userType);

        return User
                .withUsername(username)
                .password(userDTO.getPassword())
                .authorities(userTypeList) // TODO ???
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}
