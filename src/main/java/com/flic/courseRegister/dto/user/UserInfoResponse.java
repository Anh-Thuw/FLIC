package com.flic.courseRegister.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponse {
    private String fullName;
    private String email;
    private String phone;
    private String gender;
    private String birthDate;
    private String job;
    private String schoolName;
    private String studentId;
    private String idNumber;
    private String idIssuedPlace;
    private String idIssuedDate;
}
