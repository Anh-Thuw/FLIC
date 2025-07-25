package com.flic.courseRegister.dto.user;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserUpdateDTO {
    private String fullName;
    private String phone;
    private String role;
    private String status;      // "active", "inactive", "blocked"
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
}