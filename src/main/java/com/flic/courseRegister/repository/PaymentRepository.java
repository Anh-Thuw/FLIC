package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByEnrolmentId(Long enrolmentId);
    // Optional: các method tuỳ chỉnh nếu cần
}
