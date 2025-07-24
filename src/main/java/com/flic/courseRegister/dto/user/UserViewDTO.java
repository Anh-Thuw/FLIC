package com.flic.courseRegister.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserViewDTO {
    private Long id;
    private String email;
    private String fullName;
    private String avatarUrl;
    private String role;
    private String status;
}

