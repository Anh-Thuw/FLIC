package com.flic.courseRegister.controller.admin;

import com.flic.courseRegister.dto.admin.NewsDTO;
import com.flic.courseRegister.dto.admin.PaymentDTO;
import com.flic.courseRegister.service.admin.PaymentService;
import com.flic.courseRegister.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/admin/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentDTO>> updatePayment(@PathVariable Long id,@RequestBody PaymentDTO dto) {
        try {
            PaymentDTO updated = paymentService.updateStatus(id, dto);
            ApiResponse<PaymentDTO> response = new ApiResponse<>(
                    true,
                    "Cập nhật thanh toán thành công",
                    updated
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            ApiResponse<PaymentDTO> response = new ApiResponse<>(
                    false,
                    "Cập nhật thanh toán thất bại: " + ex.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}