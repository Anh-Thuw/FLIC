package com.flic.courseRegister.dto.user;

import lombok.Data;

@Data
public class UserRegisterRequestDTO {
    private String fullName;
    private String email;
    private String phone;
    private String password;
}
