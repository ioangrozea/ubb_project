package com.internshipfinder.demo.controller;

import com.internshipfinder.demo.business.dto.AdminDTO;
import com.internshipfinder.demo.business.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<Set<AdminDTO>> getAllAdmins() {
        return ResponseEntity.ok(this.adminService.getAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> findAdminById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.adminService.getAdminById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity createAdmin(@RequestBody AdminDTO adminDTO) {
        return ResponseEntity.ok(this.adminService.createAdmin(adminDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO adminDTO) {
        try {
            return ResponseEntity.ok(this.adminService.updateAdmin(id, adminDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAdmin(@PathVariable Long id) {
        this.adminService.deleteAdmin(id);
        return ResponseEntity.ok().build();
    }

}
