package com.flic.courseRegister.dto.admin;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserAdminViewDTO {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private String role;
    private String status;
    private String studentId;
    private String gender;
    private String job;
    private LocalDate birthDate;
    private String birthPlace;
    private String ethnicity;
    private String idNumber;
    private LocalDate idIssuedDate;
    private String idIssuedPlace;
    private String schoolName;
    private String avatarUrl;
    private LocalDateTime createdAt;
}
