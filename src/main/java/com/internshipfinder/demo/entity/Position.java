package com.internshipfinder.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String description;
    @Column
    private Long numberOfPositions;
    @Column
    private Long numberOccupied;

    @ManyToMany(mappedBy = "position")
    private List<Student> students = new ArrayList<>();
}
