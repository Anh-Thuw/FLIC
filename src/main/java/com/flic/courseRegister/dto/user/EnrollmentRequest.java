package com.flic.courseRegister.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentRequest {


    private String fullName;
    private String phone;
    private String gender;
    private String birthDate; // Định dạng yyyy-MM-dd
    private String job;
    private String idStudent;
    private String schoolName;
    private String idNumber;     // CCCD


    private Long courseId;


    private String paymentMethod;
    private BigDecimal amount;
    private String billImage;
    private String paymentStatus;   // tương ứng payment_status (nên nhận client cung cấp hoặc set default)
    private String paidAt;          // Có thể truyền ngày hoặc set null, dạng yyyy-MM-dd'T'HH:mm:ss
    private String note;
    private String paymentFor;
}
