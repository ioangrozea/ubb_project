package com.internshipfinder.demo.business.dto;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_STUDENT,
    ROLE_COMPANY;

    @Override
    public String getAuthority() {
        return name();
    }
}
