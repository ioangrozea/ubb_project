package com.internshipfinder.demo.business.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDTO {
    private Long id;
    private String username;
    private String password;
}
