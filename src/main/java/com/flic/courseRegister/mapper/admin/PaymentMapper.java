package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.admin.PaymentDTO;
import com.flic.courseRegister.entity.Enrollment;
import com.flic.courseRegister.entity.Payment;
import com.flic.courseRegister.repository.UserAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    @Autowired
    private UserAttachmentRepository userAttachmentRepository;

    public PaymentDTO toDto(Payment payment) {
        PaymentDTO dto = new PaymentDTO();

        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setMethod(payment.getPaymentMethod());
        dto.setBillImage(payment.getBillImage());

        dto.setCreatedAt(payment.getCreatedAt());
        dto.setUpdatedAt(payment.getUpdatedAt());
    }



}