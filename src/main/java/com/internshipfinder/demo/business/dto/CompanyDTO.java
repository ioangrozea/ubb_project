package com.internshipfinder.demo.business.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    private Long id;
    private String name;
    private String description;
    private String username;
    private String password;
}
