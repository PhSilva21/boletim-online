package com.bandeira.school_report_online.controllers;

import com.bandeira.school_report_online.dtos.LoginResponse;
import com.bandeira.school_report_online.dtos.RegisterDTO;
import com.bandeira.school_report_online.dtos.UserRequest;
import com.bandeira.school_report_online.model.User;
import com.bandeira.school_report_online.repositories.UserRepository;
import com.bandeira.school_report_online.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserRequest userRequest){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userRequest.email(), userRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        if(this.repository.findByUsername(registerDTO.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(UUID.randomUUID().toString() ,registerDTO.email(), encryptedPassword, registerDTO.userRole());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}