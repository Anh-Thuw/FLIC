package com.flic.courseRegister.service.admin.impl;

import com.flic.courseRegister.dto.admin.CourseAdminViewDTO;
import com.flic.courseRegister.dto.admin.PaymentDTO;
import com.flic.courseRegister.dto.admin.UpdateStatusPaymentDTO;
import com.flic.courseRegister.dto.user.EnrollmentResponse;
import com.flic.courseRegister.dto.user.UserProfileDTO;
import com.flic.courseRegister.entity.*;
import com.flic.courseRegister.mapper.admin.PaymentMapper;
import com.flic.courseRegister.repository.CourseRepository;
import com.flic.courseRegister.repository.EnrollmentRepository;
import com.flic.courseRegister.repository.PaymentRepository;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.admin.AdminService;
import com.flic.courseRegister.service.admin.EnrollmentAdminService;
import com.flic.courseRegister.service.admin.PaymentService;
import com.flic.courseRegister.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository         paymentRepo;
    private final PaymentMapper             paymentMapper;
    private final EnrollmentAdminService    enrollmentAdminService;
    private final UserService               userService;
    private final UserRepository            userRepo;
    private final AdminService              courseService;
    private final PasswordEncoder passwordEncoder;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepo.findAll()
                .stream()
                .map(payment -> {
                    // Map entity -> DTO
                    PaymentDTO dto = paymentMapper.toDto(payment);

                    if (payment.getEnrollment() != null) {
                        EnrollmentResponse enrollmentInfo = enrollmentAdminService.getEnrollmentById(payment.getEnrollment().getId());
                        dto.setEnrollment(enrollmentInfo);

                        // Lấy student info từ enrollment
                        if (enrollmentInfo != null && enrollmentInfo.getUserId() != null) {
                            UserProfileDTO studentInfo = userService.getUserById(enrollmentInfo.getUserId());
                            dto.setStudent(studentInfo);
                        }
                        // Lấy course info
                        if (enrollmentInfo != null && enrollmentInfo.getCourseId() != null) {
                            CourseAdminViewDTO courseInfo = courseService.getCourseById(enrollmentInfo.getCourseId());
                            dto.setCourse(courseInfo);
                        }
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO updateStatus(Long id, UpdateStatusPaymentDTO dto) {
        Payment payment = paymentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        if (dto.getStatus() != null) {
            payment.setPaymentStatus(dto.getStatus());
        }
        payment.setUpdatedAt(LocalDateTime.now());

        Payment saved = paymentRepo.save(payment);

        // Lấy user qua enrollment
        Enrollment enrollment = payment.getEnrollment();
        User user = enrollment.getUser();


        if ((user.getPasswordHash() == null || user.getPasswordHash().isBlank())
                && user.getBirthDate() != null) {

                LocalDate birthDate = user.getBirthDate();
                String rawPassword = String.format("%02d%02d%04d",
                        birthDate.getDayOfMonth(),
                        birthDate.getMonthValue(),
                        birthDate.getYear());
                user.setPasswordHash(passwordEncoder.encode(rawPassword));
        }
        enrollment.setStatus(EnrollmentStatus.IN_PROGRESS);
        enrollmentRepository.save(enrollment);
        user.setRole("STUDENT");
        User updateRole = userRepo.save(user);

        return paymentMapper.toDto(saved);
    }

    @Override
    public PaymentDTO detailPayment(Long id) {
        Payment payment = paymentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("NewsArticle not found with id: " + id));

        return paymentMapper.toDto(payment);       }


}
