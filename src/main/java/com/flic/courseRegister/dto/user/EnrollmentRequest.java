package com.flic.courseRegister.dto.user;

import lombok.Data;

@Data
public class EnrollmentRequest {
    private Long userId;
    private Long courseId;
}
