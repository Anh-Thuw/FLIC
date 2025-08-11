package com.flic.courseRegister.service.admin;

import com.flic.courseRegister.dto.user.EnrollmentResponse;

public interface EnrollmentAdminService {

    EnrollmentResponse getEnrollmentById(Long enrolmentId);
}
