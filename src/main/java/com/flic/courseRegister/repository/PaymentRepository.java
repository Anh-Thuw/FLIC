package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT p FROM Payment p JOIN FETCH p.enrollment e JOIN FETCH e.user u")
    List<Payment> findAllWithDependencies();
    // Optional: các method tuỳ chỉnh nếu cần
}
