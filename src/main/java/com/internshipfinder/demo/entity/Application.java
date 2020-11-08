package com.internshipfinder.demo.entity;

import com.internshipfinder.demo.entity.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Application {
    @EmbeddedId
    private ApplicationPK id;
    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @ManyToOne
    @MapsId("position_id")
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class ApplicationPK implements Serializable{
        @Column(name = "position_id")
        private Long positionId;
        @Column(name = "student_id")
        private Long studentId;
    }
}
