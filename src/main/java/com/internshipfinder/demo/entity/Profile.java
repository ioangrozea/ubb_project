package com.internshipfinder.demo.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column
    private String description;
    @Lob
    private byte[] cv;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Student student;
}
