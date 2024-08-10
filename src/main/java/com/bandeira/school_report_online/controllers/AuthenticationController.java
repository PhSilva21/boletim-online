package com.bandeira.school_report_online.controllers;

import com.bandeira.school_report_online.dtos.LoginResponse;
import com.bandeira.school_report_online.dtos.UserRequest;
import com.bandeira.school_report_online.model.User;
import com.bandeira.school_report_online.services.TokenService;
import com.bandeira.school_report_online.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/register")
    public ResponseEntity<UserRequest> register(@RequestBody @Valid UserRequest userRequest) throws JsonProcessingException {
        userService.createUser(userRequest);

        return ResponseEntity.ok().build();
    }
}