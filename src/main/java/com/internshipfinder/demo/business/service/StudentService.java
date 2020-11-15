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

    public boolean getStudentByUsernameAndPassword(UserDTO userDTO) throws Exception {
        this.studentRepository.findStudentByUsernameAndPassword(userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()))
                .orElseThrow(Exception::new);
        return true;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        studentDTO.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        return modelMapper.map(
                this.studentRepository.save(modelMapper.map(studentDTO, Student.class)),
                StudentDTO.class);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) throws Exception {
        Student studentOptional = this.studentRepository.findById(id).orElseThrow(Exception::new);

        studentDTO.setId(id);
        studentDTO.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        return this.modelMapper.map(this.studentRepository.save(modelMapper.map(studentDTO, Student.class)),
                StudentDTO.class);
    }

    public void deleteStudent(Long studentId) {
        this.studentRepository.deleteById(studentId);
    }
}
