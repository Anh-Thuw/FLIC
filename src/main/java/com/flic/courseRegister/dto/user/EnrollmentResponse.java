package com.flic.courseRegister.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponse {
    private Long id;          // id của enrollment
    private Long userId;      // id user đăng ký
    private Long courseId;    // id khóa học
    private Long enrollmentId; // ⬅ bổ sung
    private Long paymentId;    // ⬅ bổ sung
    private String enrollmentStatus;  // trạng thái enrollment, ví dụ "PENDING", "CONFIRMED", "CANCELLED"
    private String paymentStatus;     // trạng thái thanh toán, ví dụ "pending", "completed", "failed"
}
