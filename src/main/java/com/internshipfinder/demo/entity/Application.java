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
public class Application implements Serializable {
    @Id
    @ManyToOne
    private Position position;
    @Id
    @ManyToOne
    private Student student;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
}
