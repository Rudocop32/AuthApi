package org.pet.authapi.service;

import org.pet.authapi.model.User;
import org.pet.authapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean login(String login, String password) {
        Optional<User> userOpt = userRepository.findByLogin(login);

        if (userOpt.isPresent()) {
            // Пользователь найден – проверка пароля
            return passwordEncoder.matches(password, userOpt.get().getPassword());
        } else {
            // Пользователь не найден – регистрируем
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setPassword(passwordEncoder.encode(password));
            userRepository.save(newUser);
            return true; // Зарегистрирован и вошел
        }
    }
    public boolean register(String login, String password) {
        Optional<User> userOpt = userRepository.findByLogin(login);
        if (userOpt.isPresent()) {
            return false;
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return true;
    }
}