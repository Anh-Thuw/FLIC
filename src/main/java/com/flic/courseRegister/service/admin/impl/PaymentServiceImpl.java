package com.flic.courseRegister.service.admin.impl;

import com.flic.courseRegister.dto.admin.PaymentDTO;
import com.flic.courseRegister.dto.user.EnrollmentResponse;
import com.flic.courseRegister.dto.user.UserProfileDTO;
import com.flic.courseRegister.mapper.admin.PaymentMapper;
import com.flic.courseRegister.repository.PaymentRepository;
import com.flic.courseRegister.service.admin.EnrollmentAdminService;
import com.flic.courseRegister.service.admin.PaymentService;
import com.flic.courseRegister.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepo;
    private final PaymentMapper paymentMapper;
    private final EnrollmentAdminService enrollmentAdminService;
    private final UserService userService;

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepo.findAll()
                .stream()
                .map(payment -> {
                    // Map entity -> DTO
                    PaymentDTO dto = paymentMapper.toDto(payment);

                    // Lấy enrollment info từ enrolmentId
                    if (payment.getEnrolmentId() != null) {
                        EnrollmentResponse enrollmentInfo = enrollmentAdminService.getEnrollmentById(payment.getEnrolmentId());
                        dto.setEnrollment(enrollmentInfo);

                        // Lấy student info từ enrollment
                        if (enrollmentInfo != null && enrollmentInfo.getUserId() != null) {
                            UserProfileDTO studentInfo = userService.getUserById(enrollmentInfo.getUserId());
                            dto.setStudent(studentInfo);
                        }
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }
}
