package com.flic.courseRegister.controller;

import com.flic.courseRegister.dto.user.LoginRequest;
import com.flic.courseRegister.dto.user.LoginResponse;
import com.flic.courseRegister.dto.user.UserRegisterRequestDTO;
import com.flic.courseRegister.dto.user.UserViewDTO;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.security.JwtUtil;
import com.flic.courseRegister.service.user.AuthService;
import com.flic.courseRegister.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserViewDTO> register(@RequestBody UserRegisterRequestDTO request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = authService.authenticate(request.getEmail(), request.getPassword());

        if (user == null) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
