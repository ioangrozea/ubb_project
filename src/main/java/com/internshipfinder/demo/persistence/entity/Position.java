package com.internshipfinder.demo.persistence.entity;

import lombok.*;

import javax.persistence.*;

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
    private String title;
    @Column
    private String description;
    @Column
    private Long numberOfPositions;
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

}
