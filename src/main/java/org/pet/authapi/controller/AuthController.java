package org.pet.authapi.controller;

import org.pet.authapi.dto.LoginRequest;
import org.pet.authapi.dto.RegisterRequest;
import org.pet.authapi.response.AuthResponse;
import org.pet.authapi.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        try {
            service.register(req.getLogin(), req.getPassword());
            return ResponseEntity.ok(new AuthResponse("Регистрация успешна"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        boolean ok = service.authenticate(req.getLogin(), req.getPassword());
        if (ok) return ResponseEntity.ok(new AuthResponse("Вход разрешён"));
        return ResponseEntity.status(401).body(new AuthResponse("Неверные логин или пароль"));
    }
}