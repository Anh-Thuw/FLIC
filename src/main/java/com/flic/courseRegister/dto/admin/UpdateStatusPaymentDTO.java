package com.flic.courseRegister.dto.admin;

import com.flic.courseRegister.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UpdateStatusPaymentDTO {
    private Payment.StatusPayment status;
}
