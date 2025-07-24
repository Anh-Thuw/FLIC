package com.flic.courseRegister.mapper.user;

import com.flic.courseRegister.dto.user.UserCreateDTO;
import com.flic.courseRegister.dto.user.UserViewDTO;
import com.flic.courseRegister.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public UserViewDTO toDto(User user) {
        return UserViewDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .avatarUrl(user.getAvatarUrl())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }

    public User toEntity(UserCreateDTO dto) {
        return User.builder()
                .email(dto.getEmail())
                .passwordHash(hashPassword(dto.getPassword()))
                .fullName(dto.getFullName())
                .role("user")
                .status("active")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    private String hashPassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }
}
