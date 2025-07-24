package com.flic.courseRegister.mapper;

import com.flic.courseRegister.dto.UserAdminViewDTO;
import com.flic.courseRegister.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserAdminMapper {
    public UserAdminViewDTO toDto(User user) {
        return UserAdminViewDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .role(user.getRole())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
