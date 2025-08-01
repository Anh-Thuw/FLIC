package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.user.SubmissionRequestDTO;
import com.flic.courseRegister.dto.user.SubmissionViewDTO;
import com.flic.courseRegister.entity.Assignment;
import com.flic.courseRegister.entity.Enrollment;
import com.flic.courseRegister.mapper.user.SubmissionMapper;
import com.flic.courseRegister.repository.AssignmentRepository;
import com.flic.courseRegister.repository.EnrollmentRepository;
import com.flic.courseRegister.repository.SubmissionRepository;
import com.flic.courseRegister.service.user.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final AssignmentRepository assignmentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final SubmissionRepository submissionRepository;
    private final SubmissionMapper submissionMapper;

    @Override
    public SubmissionViewDTO submitAssignment(SubmissionRequestDTO dto) {
        Assignment assignment = assignmentRepository.findById(dto.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        Enrollment enrollment = enrollmentRepository.findById(dto.getEnrollmentId())
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        var submission = submissionMapper.toEntity(dto, assignment, enrollment);
        return submissionMapper.toViewDTO(submissionRepository.save(submission));
    }
}
