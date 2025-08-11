package com.flic.courseRegister.dto.user;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EnrollmentRequest {

    private Long courseId;


    private String paymentMethod;
    private BigDecimal amount;
    private String billImage;
    private String paymentStatus;   // tương ứng payment_status (nên nhận client cung cấp hoặc set default)
    private String paidAt;          // Có thể truyền ngày hoặc set null, dạng yyyy-MM-dd'T'HH:mm:ss
    private String note;
    private String paymentFor;
}
