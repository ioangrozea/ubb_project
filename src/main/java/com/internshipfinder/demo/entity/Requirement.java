package com.internshipfinder.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Requirement implements Serializable {
    @Id
    @ManyToOne
    private Position position;
    @Id
    @ManyToOne
    private Ability ability;

    @Enumerated(EnumType.STRING)
    private Level level;
}
