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
    private String idNumber;
    private String gender;
    private String birthDate;
    private String idStudent;
    private String job;
    private String birthPlace;
    private String schoolName;
    private String idIssuedDate;
    private String idIssuedPlace;
    private String ethnicity;
    private String email;
    private String phone;
    private String fullName;
    private String avatarUrl;
    private String role;
    private String status;
}

