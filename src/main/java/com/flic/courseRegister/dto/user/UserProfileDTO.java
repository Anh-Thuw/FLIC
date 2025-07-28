package com.flic.courseRegister.dto.user;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class UserProfileDTO {
    private long id;
    private String email;
    private String fullName;
    private String phone;
    private String avatarUrl;
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

    private AttachmentDTO _4x6Photo;
    private AttachmentDTO _3x4Photo;
    private AttachmentDTO birthCertificate;
}
