package com.internshipfinder.demo.business.dto;

import lombok.Data;

@Data
public class PositionDTO {
    private Long id;
    private Long companyId;
    private String title;
    private String description;
    private Long numberOfPositions;
}
