package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Optional: các method tuỳ chỉnh nếu cần
}
