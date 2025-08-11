package com.flic.courseRegister.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enrollment_id")
    private Long enrolmentId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "method")
    private String paymentMethod;

    @Column(name = "status")
    private String paymentStatus;

    @Column(name ="bill_image")
    private String billImage;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_for", columnDefinition = "ENUM('course', 'exam')")
    private PaymentFor paymentFor;

    @Column(name ="note")
    private String notePayment;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    // Enum cho payment_for
    public enum PaymentFor {
        course,
        exam
    }
}

