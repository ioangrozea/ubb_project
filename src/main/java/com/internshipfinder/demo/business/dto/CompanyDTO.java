package com.internshipfinder.demo.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDTO {
    private Long id;
    private String name;
    private String description;
    private String username;
    private String password;
    private boolean acceptedByAdmin;
    private Set<PositionDTO> positions;
}
