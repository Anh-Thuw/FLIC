package com.flic.courseRegister.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String fullName;
}

