package com.internshipfinder.demo.controller;

import com.internshipfinder.demo.business.dto.PositionDTO;
import com.internshipfinder.demo.business.dto.PositionDetailsDTO;
import com.internshipfinder.demo.business.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("position")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;

    @GetMapping
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<Set<PositionDTO>> getAllPositions() {
        return ResponseEntity.ok(this.positionService.getAllPositions());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<PositionDetailsDTO> findPositionById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.positionService.getPositionById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity createPosition(@RequestBody PositionDTO positionDTO) {
        this.positionService.createPosition(positionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<PositionDTO> updatePosition(@PathVariable Long id, @RequestBody PositionDTO positionDTO) {
        try {
            return ResponseEntity.ok(this.positionService.updatePosition(id, positionDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity deletePosition(@PathVariable Long id) {
        this.positionService.deletePosition(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("company/{companyId}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<Set<PositionDTO>> findAllCompanyPositions(@PathVariable Long companyId) {
        try {
            return ResponseEntity.ok(this.positionService.getAllCompanyPositions(companyId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
