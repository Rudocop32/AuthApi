package org.pet.authapi.controller;

import org.pet.authapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password) {
        if(authService.login(login, password)) {
            return "login successful";
        }
        return "login failed";
    }

    @PostMapping("/register")
    public String register(@RequestParam String login, @RequestParam String password) {
        if(authService.register(login, password)) {
            return "Registration successful!";
        }
        return "Invalid credentials!";
    }

}