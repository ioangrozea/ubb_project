package com.internshipfinder.demo.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionDTO {
    private Long id;
    private String title;
    private Long numberOfPositions;
    private LocalDate createdAt;
    private Long companyId;
    private String companyName;
    private String description;
    private String url;
    private String email;
}
