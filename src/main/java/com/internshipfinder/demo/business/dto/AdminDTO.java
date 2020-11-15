package com.internshipfinder.demo.business.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminDTO {
    private Long id;
    private String username;
    private String password;
}
