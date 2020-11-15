package com.internshipfinder.demo.business.facade;

import com.internshipfinder.demo.business.dto.UserDTO;
import com.internshipfinder.demo.business.service.AdminService;
import com.internshipfinder.demo.business.service.CompanyService;
import com.internshipfinder.demo.business.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserLoginFacade {
    private final AdminService adminService;
    private final StudentService studentService;
    private final CompanyService companyService;

    public ResponseEntity login(UserDTO userDTO) {
        try {
            switch (userDTO.getUserType()) {
                case ADMIN:
                    this.adminService.getAdminByUsernameAndPassword(userDTO);
                    return ResponseEntity.ok().build(); //JWT header
                case COMPANY:
                    this.companyService.getCompanyByUsernameAndPassword(userDTO);
                    return ResponseEntity.ok().build(); //JWT header
                case STUDENT:
                    this.studentService.getStudentByUsernameAndPassword(userDTO);
                    return ResponseEntity.ok().build(); //JWT header
                default:
                    return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
