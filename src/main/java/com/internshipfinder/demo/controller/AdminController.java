package com.internshipfinder.demo.controller;

import com.internshipfinder.demo.business.dto.AdminDTO;
import com.internshipfinder.demo.business.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<AdminDTO>> getAllAdmins() {
        return ResponseEntity.ok(this.adminService.getAdmins());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminDTO> findAdminById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.adminService.getAdminById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity createAdmin(@RequestBody AdminDTO adminDTO) {
        this.adminService.createAdmin(adminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO adminDTO) {
        try {
            return ResponseEntity.ok(this.adminService.updateAdmin(id, adminDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteAdmin(@PathVariable Long id) {
        this.adminService.deleteAdmin(id);
        return ResponseEntity.ok().build();
    }

}
