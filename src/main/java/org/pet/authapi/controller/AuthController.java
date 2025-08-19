package org.pet.authapi.controller;

import org.pet.authapi.dto.LoginRequest;
import org.pet.authapi.dto.RegisterRequest;
import org.pet.authapi.response.AuthResponse;
import org.pet.authapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController( AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        if(authService.register(req.getLogin(), req.getPassword())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("Register successful!"));
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthResponse("User already exists!"));
        }


    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        if(authService.authenticate(req.getLogin(), req.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse("Login successful!"));
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Invalid login or password!"));
        }
    }
}