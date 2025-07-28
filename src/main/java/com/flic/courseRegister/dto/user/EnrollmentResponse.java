package com.flic.courseRegister.dto.user;

import lombok.Data;

@Data
public class EnrollmentResponse {
    private Long id;
    private Long userId;
    private Long courseId;
    private String status;
}
