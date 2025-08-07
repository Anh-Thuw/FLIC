package com.flic.courseRegister.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileUpdateRequestDTO {
    private String fullName;
    private String phone;
    private String gender;
    private LocalDate birthDate;
    private String schoolName;
    private String job;
    private String birthPlace;
    private String ethnicity;
}
