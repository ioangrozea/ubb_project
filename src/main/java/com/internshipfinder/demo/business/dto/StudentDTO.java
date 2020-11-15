package com.internshipfinder.demo.business.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Data
public class StudentDTO {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime birthdate;
    private String description;
    private byte[] cv;
}
