package com.flic.courseRegister.service.user;

import com.flic.courseRegister.dto.user.EnrollmentRequest;
import com.flic.courseRegister.dto.user.EnrollmentResponse;
import com.flic.courseRegister.dto.user.PublicEnrollmentRequest;
import org.springframework.web.multipart.MultipartFile;

public interface EnrollmentService {
    // Đăng ký với user đã đăng nhập (giữ lại nếu cần)
    EnrollmentResponse enroll(EnrollmentRequest request, MultipartFile file);

    // Đăng ký công khai (public) không cần đăng nhập
    EnrollmentResponse publicEnroll(PublicEnrollmentRequest request, MultipartFile file);
}
