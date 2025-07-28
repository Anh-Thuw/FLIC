package com.flic.courseRegister.service.user;

import com.flic.courseRegister.dto.user.EnrollmentRequest;
import com.flic.courseRegister.dto.user.EnrollmentResponse;

public interface EnrollmentService {
    EnrollmentResponse enroll(EnrollmentRequest request);
}
