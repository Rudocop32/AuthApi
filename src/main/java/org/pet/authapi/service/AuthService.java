package org.pet.authapi.service;

import org.pet.authapi.model.User;
import org.pet.authapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserRepository users;

    @Autowired
    private final PasswordEncoder encoder;

    public AuthService(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    @Transactional
    public void register(String username, String rawPassword) {
        if (users.existsByLogin(username)) {
            throw new IllegalArgumentException("Пользователь уже существует");
        }
        String hash = encoder.encode(rawPassword);
        users.save(new User(username, hash));
    }

    @Transactional(readOnly = true)
    public boolean authenticate(String username, String rawPassword) {
        return users.findByLogin(username)
                .map(u -> encoder.matches(rawPassword, u.getPassword()))
                .orElse(false);
    }
}