package com.internshipfinder.demo.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean acceptedByAdmin;
    @JsonManagedReference
    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Position> positions = new HashSet<>();
}
