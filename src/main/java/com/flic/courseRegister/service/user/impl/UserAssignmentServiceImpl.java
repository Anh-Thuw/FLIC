package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.user.AssignmentViewDTO;
import com.flic.courseRegister.entity.Assignment;
import com.flic.courseRegister.mapper.user.AssignmentMapper;
import com.flic.courseRegister.repository.AssignmentRepository;
import com.flic.courseRegister.repository.EnrollmentRepository;
import com.flic.courseRegister.repository.SubmissionRepository;
import com.flic.courseRegister.security.AuthUtils;
import com.flic.courseRegister.service.user.UserAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserAssignmentServiceImpl implements UserAssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;
    private final EnrollmentRepository enrollmentRepository;
    private final SubmissionRepository submissionRepository;

    @Override
    public List<AssignmentViewDTO> getAssignmentsByLessonId(Long lessonId) {
       Long userId = AuthUtils.currentUserId();

       // lấy tất cả assign của lesson
        List<Assignment> assignments = assignmentRepository.findByLessonId(lessonId);
        if (assignments.isEmpty()) return List.of();

        // tìm enrollment của user trong course chứa lesson
        Optional<Long> enrOpt = enrollmentRepository.findEnrollmentIdByLessonIdAndUserId(lessonId, userId);

        // nếu chưa enroll -> submitted = false
        List<AssignmentViewDTO> dtos = assignmentMapper.toViewDtoList(assignments);
        if (enrOpt.isEmpty()) {
            dtos.forEach(d -> d.setSubmitted(false));
            return dtos;
        }

        Long enrollmentId = enrOpt.get();

        // lấy set các assignment đã nộp (1 query)
        List<Long> aIds = assignments.stream().map(Assignment::getId).toList();
        Set<Long> submittedIds = submissionRepository.findSubmittedAssignmentIds(enrollmentId, aIds);

        // án cgờ submitted vào DTO
        dtos.forEach(d -> d.setSubmitted(submittedIds.contains(d.getId())));
        return dtos;
    }
}
