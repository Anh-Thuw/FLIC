package com.flic.courseRegister.dto.user;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PublicEnrollmentRequest {
    // User info (theo trường trong form người dùng)
    private String email;
    private String fullName;
    private String phone;
    private String gender;
    private String birthDate; // Định dạng yyyy-MM-dd
    private String job;
    private String idStudent;
    private String schoolName;
    private String idNumber;     // CCCD
    private String idIssuedPlace;
    private String idIssuedDate; // yyyy-MM-dd

    // Thông tin khóa học cần đăng ký
    private Long courseId;

    // Thông tin thanh toán
    private String paymentMethod;
    private BigDecimal amount;
    private String billImage;
    private String paymentStatus;   // tương ứng payment_status (nên nhận client cung cấp hoặc set default)
    private String paidAt;          // Có thể truyền ngày hoặc set null, dạng yyyy-MM-dd'T'HH:mm:ss
    private String note;
    private String paymentFor;
}
