package com.flic.courseRegister.service.admin.impl;

import com.flic.courseRegister.dto.user.EnrollmentResponse;
import com.flic.courseRegister.entity.Enrollment;
import com.flic.courseRegister.exception.ResourceNotFoundException;
import com.flic.courseRegister.mapper.user.EnrollmentMapper;
import com.flic.courseRegister.repository.EnrollmentRepository;
import com.flic.courseRegister.service.admin.EnrollmentAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentAdminServiceImpl implements EnrollmentAdminService {

    private final EnrollmentRepository enrollmentRepo;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentResponse getEnrollmentById(Long enrolmentId) {
        Enrollment enrollment = enrollmentRepo.findById(enrolmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy enrollment với id: " + enrolmentId
                ));

        return enrollmentMapper.toDto(enrollment);
    }
}
