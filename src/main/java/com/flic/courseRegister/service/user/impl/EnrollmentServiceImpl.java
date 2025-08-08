package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.user.EnrollmentRequest;
import com.flic.courseRegister.dto.user.EnrollmentResponse;
import com.flic.courseRegister.dto.user.PublicEnrollmentRequest;
import com.flic.courseRegister.entity.*;
import com.flic.courseRegister.exception.ResourceNotFoundException;
import com.flic.courseRegister.mapper.user.EnrollmentMapper;
import com.flic.courseRegister.repository.CourseRepository;
import com.flic.courseRegister.repository.EnrollmentRepository;
import com.flic.courseRegister.repository.PaymentRepository;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.user.EnrollmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final PaymentRepository paymentRepository;    // <-- bổ sung field này
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            UserRepository userRepository,
            CourseRepository courseRepository,
            PaymentRepository paymentRepository,           // <-- inject đúng
            EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.paymentRepository = paymentRepository;        // <-- gán vào biến field đã tạo
        this.enrollmentMapper = enrollmentMapper;
    }

    @Override
    public EnrollmentResponse enroll(EnrollmentRequest request) {
        if (enrollmentRepository.existsByUserIdAndCourseId(request.getUserId(), request.getCourseId())) {
            throw new IllegalArgumentException("Bạn đã đăng ký khóa học này rồi");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user với ID: " + request.getUserId()));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khóa học với ID: " + request.getCourseId()));

        Enrollment enrollment = Enrollment.builder()
                .user(user)
                .course(course)
                .status(EnrollmentStatus.PENDING)
                .progress(BigDecimal.ZERO)
                .build();

        Enrollment saved = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDto(saved);
    }
    @Override
    @Transactional
    public EnrollmentResponse publicEnroll(PublicEnrollmentRequest req) {
        // 1. Tạo mới User
        User user = User.builder()
                .email(req.getEmail())
                .fullName(req.getFullName())
                .phone(req.getPhone())
                .gender(req.getGender())
                .birthDate(req.getBirthDate() != null ? LocalDate.parse(req.getBirthDate()) : null)
                .job(req.getJob())
                .schoolName(req.getSchoolName())
                .idNumber(req.getIdNumber())
                .idIssuedPlace(req.getIdIssuedPlace())
                .idIssuedDate(req.getIdIssuedDate() != null ? LocalDate.parse(req.getIdIssuedDate()) : null)
                .role("guest")
                .status("active")
                .build();
        user = userRepository.save(user);

        // 2. Lấy Course theo courseId
        Course course = courseRepository.findById(req.getCourseId())
                .orElseThrow(() -> new RuntimeException("Khóa học không tồn tại"));

        // 3. Tạo Enrollment record
        Enrollment enrollment = Enrollment.builder()
                .user(user)
                .course(course)
                .status(EnrollmentStatus.PENDING)
                .progress(BigDecimal.ZERO)
                .enrolledAt(LocalDateTime.now())
                .build();

        enrollment = enrollmentRepository.save(enrollment);

        // 4. Tạo Payment record sử dụng enrolmentId thay vì Enrollment entity
        Payment payment = Payment.builder()
                .enrolmentId(enrollment.getId())  // gán id enrollment (Long)
                .amount(req.getAmount())
                .paymentMethod(req.getPaymentMethod())
                .billImage(req.getBillImage())
                .notePayment(req.getNote())
                .paymentStatus(req.getPaymentStatus() != null ? req.getPaymentStatus() : "pending")
                .paidAt(req.getPaidAt() != null ? LocalDateTime.parse(req.getPaidAt()) : null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        payment = paymentRepository.save(payment);

        // 5. Trả về response
        return EnrollmentResponse.builder()
                .userId(user.getId())
                .enrollmentId(enrollment.getId())
                .paymentId(payment.getId())
                .courseId(course.getId())
                .build();
    }

}
