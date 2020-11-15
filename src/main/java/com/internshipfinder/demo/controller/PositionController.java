package com.internshipfinder.demo.controller;

import com.internshipfinder.demo.business.dto.PositionDTO;
import com.internshipfinder.demo.business.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("position")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;

    @GetMapping
    public ResponseEntity<Set<PositionDTO>> getAllPositions() {
        return ResponseEntity.ok(this.positionService.getAllPositions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDTO> findPositionById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.positionService.getPositionById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity createPosition(@RequestBody PositionDTO positionDTO) {
        this.positionService.createPosition(positionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PositionDTO> updatePosition(@PathVariable Long id, @RequestBody PositionDTO positionDTO) {
        try {
            return ResponseEntity.ok(this.positionService.updatePosition(id, positionDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePosition(@PathVariable Long id) {
        this.positionService.deletePosition(id);
        return ResponseEntity.ok().build();
    }
}
