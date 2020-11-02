package com.internshipfinder.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @MapsId("abilityId")
    @JoinColumn(name = "ability_id")
    private Ability ability;

    @ManyToOne
    @MapsId("profileId")
    @JoinColumn(name = "profile_id")
    private StudentProfile profile;

    @Enumerated(EnumType.STRING)
    private Level level;
}
