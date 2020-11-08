package com.internshipfinder.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Application {
    @EmbeddedId
    private ApplicationId id;

    @ManyToOne
    @JoinColumn(name = "fk_student", insertable = false, updatable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "fk_position", insertable = false, updatable = false)
    private Position position;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Application(Student student, Position position, Status status) {
        this.id = new ApplicationId(student.getId(), position.getId());

        this.student = student;
        this.position = position;
        this.status = status;

        position.getStudents().add(student);
        student.getPositions().add(position);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class ApplicationId implements Serializable {
        private static final long serialVersionUID = 1L;

        @Column(name = "fk_student")
        private Long studentId;
        @Column(name = "fk_position")
        private Long positionId;
    }
}
