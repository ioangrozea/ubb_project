package com.internshipfinder.demo.controller;

import com.internshipfinder.demo.business.dto.StudentDTO;
import com.internshipfinder.demo.business.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<Set<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(this.studentService.getStudents());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<StudentDTO> findStudentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.studentService.getStudentById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity createStudent(@RequestBody StudentDTO studentDTO) {
        this.studentService.createStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        try {
            return ResponseEntity.ok(this.studentService.updateStudent(id, studentDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
