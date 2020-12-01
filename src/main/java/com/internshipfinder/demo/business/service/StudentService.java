package com.internshipfinder.demo.business.service;

import com.internshipfinder.demo.business.dto.StudentDTO;
import com.internshipfinder.demo.business.dto.UserDTO;
import com.internshipfinder.demo.persistence.entity.Student;
import com.internshipfinder.demo.persistence.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public Set<StudentDTO> getStudents() {
        Iterable<Student> students = this.studentRepository.findAll();
        return StreamSupport
                .stream(students.spliterator(), false)
                .map(c -> this.modelMapper.map(c, StudentDTO.class))
                .collect(Collectors.toSet());
    }

    public StudentDTO getStudentById(Long studentId) throws Exception {
        return modelMapper.map(
                this.studentRepository.findById(studentId).orElseThrow(Exception::new),
                StudentDTO.class);
    }

    public StudentDTO getStudentByUsername(UserDTO userDTO) throws Exception {
        StudentDTO foundStudentDTO = this.modelMapper.map(this.studentRepository.findStudentByUsername(userDTO.getUsername())
                .orElseThrow(Exception::new), StudentDTO.class);
        if (!this.passwordEncoder.matches(userDTO.getPassword(), foundStudentDTO.getPassword())) {
            throw new Exception();
        }
        return foundStudentDTO;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        studentDTO.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        return modelMapper.map(
                this.studentRepository.save(modelMapper.map(studentDTO, Student.class)),
                StudentDTO.class);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) throws Exception {
        Student student = this.studentRepository.findById(id).orElseThrow(Exception::new);

        studentDTO.setId(id);
        if (studentDTO.getFirstName() != null && !studentDTO.getFirstName().isEmpty()) {
           student.setFirstName(studentDTO.getFirstName());
        }
        if (studentDTO.getLastName() != null && !studentDTO.getLastName().isEmpty()) {
            student.setLastName(studentDTO.getLastName());
        }
        if (studentDTO.getUsername() != null && !studentDTO.getUsername().isEmpty()) {
            student.setUsername(studentDTO.getUsername());
        }
        if (studentDTO.getPassword() != null && !studentDTO.getPassword().isEmpty()) {
            student.setPassword(this.passwordEncoder.encode(studentDTO.getPassword()));
        }

        return this.modelMapper.map(this.studentRepository.save(student), StudentDTO.class);
    }

    public void deleteStudent(Long studentId) {
        this.studentRepository.deleteById(studentId);
    }
}
