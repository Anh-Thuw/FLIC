package com.flic.courseRegister.dto.admin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserByRoleDTO {
    private String email;
    private String fullName;
    private String password;
    private String role;
}
