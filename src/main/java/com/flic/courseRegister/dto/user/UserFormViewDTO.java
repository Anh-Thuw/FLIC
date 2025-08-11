package com.flic.courseRegister.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFormViewDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String idStudent;
    private String idNumber;
    private String gender;
    private String birthDate;
}
