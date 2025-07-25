package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.admin.UserAdminViewDTO;
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
