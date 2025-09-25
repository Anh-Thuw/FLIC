package com.flic.courseRegister.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ChangePasswordDTO {
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;
}
