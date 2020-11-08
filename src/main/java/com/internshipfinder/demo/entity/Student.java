package com.internshipfinder.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private LocalDateTime birthdate;

    @OneToMany(mappedBy = "student")
    private List<Position> positions = new ArrayList<>();
}
