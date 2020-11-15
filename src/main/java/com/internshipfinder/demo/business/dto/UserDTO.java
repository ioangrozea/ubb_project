package com.internshipfinder.demo.business.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private UserType userType;
}
