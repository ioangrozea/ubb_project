package com.internshipfinder.demo.controller;

import com.internshipfinder.demo.business.dto.UserDTO;
import com.internshipfinder.demo.business.facade.UserLoginFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {
    private final UserLoginFacade userLoginFacade;

    @PostMapping
    public ResponseEntity login(UserDTO userDTO) {
        return this.userLoginFacade.login(userDTO);
    }
}
