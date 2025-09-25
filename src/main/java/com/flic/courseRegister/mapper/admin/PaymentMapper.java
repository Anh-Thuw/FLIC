package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.admin.PaymentDTO;
import com.flic.courseRegister.entity.Enrollment;
import com.flic.courseRegister.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

    public PaymentDTO toDto(Payment payment) {
        if (payment == null) return null;
        return PaymentDTO.builder()
                .id(payment.getId())
                .enrollmentId(payment.getEnrollment() != null ? payment.getEnrollment().getId() : null)
                .amount(payment.getAmount())
                .method(payment.getPaymentMethod())
                .billImage(payment.getBillImage())
                .status(payment.getPaymentStatus())
                .note(payment.getNotePayment())
                .paymentFor(payment.getPaymentFor() != null ? payment.getPaymentFor().name() : null)
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();
    }
    public Payment toEntity(PaymentDTO dto) {
        if (dto == null) return null;
        Enrollment enrollment = null;
        if (dto.getEnrollmentId() != null) {
            enrollment = Enrollment.builder()
                    .id(dto.getEnrollmentId())
                    .build();
        }
        return Payment.builder()
                .id(dto.getId())
                .enrollment(enrollment)
                .amount(dto.getAmount())
                .paymentMethod(dto.getMethod())
                .billImage(dto.getBillImage())
                .paymentStatus(dto.getStatus())
                .notePayment(dto.getNote())
                .paymentFor(dto.getPaymentFor() != null
                        ? Payment.PaymentFor.valueOf(dto.getPaymentFor())
                        : null)
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

}
