package com.internshipfinder.demo.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Long numberOfPositions;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(id, position.id) &&
                Objects.equals(title, position.title) &&
                Objects.equals(description, position.description) &&
                Objects.equals(numberOfPositions, position.numberOfPositions) &&
                Objects.equals(company, position.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, numberOfPositions);
    }
}
