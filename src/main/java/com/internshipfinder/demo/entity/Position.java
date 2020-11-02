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
public class Position {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Company company;
    @OneToMany
    private Set<Requirement> requirements;
    @Column
    private String information;
    @Column
    private Integer nrPositions;
    @Column
    private Integer nrOccupiedPositions;

}
