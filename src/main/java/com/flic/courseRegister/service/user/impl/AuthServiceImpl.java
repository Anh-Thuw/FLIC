package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.user.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null || !passwordEncoder.matches(password, user.getPasswordHash())) {
            return null;
        }

        return user;
    }
}
