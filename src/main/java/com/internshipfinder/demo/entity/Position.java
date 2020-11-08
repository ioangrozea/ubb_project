package com.internshipfinder.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    @OneToMany(mappedBy = "position",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Application> applications = new HashSet<>();
    @OneToMany(mappedBy = "position",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Offer> offers = new HashSet<>();
}
