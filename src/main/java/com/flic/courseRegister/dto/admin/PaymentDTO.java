package com.flic.courseRegister.dto.admin;

import com.flic.courseRegister.dto.user.EnrollmentResponse;
import com.flic.courseRegister.dto.user.UserProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Long                id;
    private Long                enrollmentId;
    private BigDecimal          amount;
    private String              method;
    private String              billImage;
    private String              status;
    private String              note;
    private String              paymentFor; // hoặc Enum nếu bạn dùng PaymentFor enum
    private LocalDateTime       createdAt;
    private LocalDateTime       updatedAt;

    private EnrollmentResponse  enrollment;
    private UserProfileDTO      student;
    private CourseAdminViewDTO  course;
}
