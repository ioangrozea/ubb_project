package com.internshipfinder.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfile {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String description;
    @Lob
    private byte[] cv;
    @OneToOne
    private Student student;
    @OneToMany
    private Set<Experience> experiences;
}
