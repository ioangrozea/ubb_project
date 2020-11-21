package com.internshipfinder.demo.business.facade;

import com.internshipfinder.demo.business.dto.UserDTO;
import com.internshipfinder.demo.business.security.JwtTokenProvider;
import com.internshipfinder.demo.business.service.AdminService;
import com.internshipfinder.demo.business.service.CompanyService;
import com.internshipfinder.demo.business.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserLoginFacade {
    private final AdminService adminService;
    private final StudentService studentService;
    private final CompanyService companyService;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity login(UserDTO userDTO) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            switch (userDTO.getUserType()) {
                case ROLE_ADMIN:
                    this.adminService.getAdminByUsername(userDTO);
                    httpHeaders = new HttpHeaders();
                    httpHeaders.add("Authorization", "Bearer " +
                            jwtTokenProvider.createToken(userDTO.getUsername(), userDTO.getUserType()));
                    return ResponseEntity.ok().headers(httpHeaders).build(); //JWT header
                case ROLE_COMPANY:
                    this.companyService.getCompanyByUsername(userDTO);
                    httpHeaders = new HttpHeaders();
                    httpHeaders.add("Authorization", "Bearer " +
                            jwtTokenProvider.createToken(userDTO.getUsername(), userDTO.getUserType()));
                    return ResponseEntity.ok().headers(httpHeaders).build(); //JWT header
                case ROLE_STUDENT:
                    this.studentService.getStudentByUsername(userDTO);
                    httpHeaders = new HttpHeaders();
                    httpHeaders.add("Authorization", "Bearer " +
                            jwtTokenProvider.createToken(userDTO.getUsername(), userDTO.getUserType()));
                    return ResponseEntity.ok().headers(httpHeaders).build(); //JWT header
                default:
                    return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
