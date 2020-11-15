package com.internshipfinder.demo.business.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private UserType userType;
}
