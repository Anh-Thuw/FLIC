package com.flic.courseRegister.service.admin;

import com.flic.courseRegister.dto.admin.PaymentDTO;

import java.util.List;

public interface PaymentService {
    public List<PaymentDTO> getAllPayments() ;

    PaymentDTO updateStatus(Long id, PaymentDTO dto);
}
