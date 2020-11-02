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
public class Ability {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    @Column
    private String description;
    @OneToMany
    private Set<Experience> experiences;
    @OneToMany
    private Set<Requirement> requirements;
}
