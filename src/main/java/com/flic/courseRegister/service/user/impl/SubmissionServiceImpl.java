// SubmissionServiceImpl.java
package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.user.SubmissionRequestDTO;
import com.flic.courseRegister.dto.user.SubmissionViewDTO;
import com.flic.courseRegister.entity.Assignment;
import com.flic.courseRegister.entity.Enrollment;
import com.flic.courseRegister.entity.Submission;
import com.flic.courseRegister.repository.AssignmentRepository;
import com.flic.courseRegister.repository.EnrollmentRepository;
import com.flic.courseRegister.repository.SubmissionRepository;
import com.flic.courseRegister.security.AuthUtils;
import com.flic.courseRegister.service.user.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final AssignmentRepository assignmentRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final SubmissionRepository submissionRepo;

    @Override
    @Transactional
    public SubmissionViewDTO submitAssignment(SubmissionRequestDTO dto) {
        Long userId = AuthUtils.currentUserId();

        // 1) Tìm assignment + lesson + course
        Assignment assignment = assignmentRepo.findWithLessonAndCourseById(dto.getAssignmentId())
                .orElseThrow(() -> new IllegalArgumentException("Assignment không tồn tại"));

        // (tuỳ chọn) chặn quá hạn
        if (assignment.getDueDate() != null && LocalDateTime.now().isAfter(assignment.getDueDate())) {
            throw new IllegalStateException("Đã quá hạn nộp");
        }

        // 2) Suy ra enrollmentId từ assignmentId + userId
        Long enrollmentId = enrollmentRepo
                .findEnrollmentIdByAssignmentIdAndUserId(assignment.getId(), userId)
                .orElseThrow(() -> new IllegalStateException("Bạn chưa enroll khóa học của bài tập này"));

        // (tuỳ chọn) chặn nộp trùng (nếu policy 1 lần)
        submissionRepo.findFirstByAssignmentIdAndEnrollmentId(assignment.getId(), enrollmentId)
                .ifPresent(s -> {
                    throw new IllegalStateException("Bạn đã nộp bài này rồi");
                });

        // 3) Lưu submission
        Submission entity = Submission.builder()
                .assignment(Assignment.builder().id(assignment.getId()).build())
                .enrollment(Enrollment.builder().id(enrollmentId).build())
                .fileUrl(dto.getFileUrl())
                .submittedAt(LocalDateTime.now())
                .build();

        submissionRepo.save(entity);

        // 4) Map về view DTO (tuỳ mapper của bạn)
        return SubmissionViewDTO.builder()
                .id(entity.getId())
                .assignmentId(assignment.getId())
                .enrollmentId(enrollmentId)
                .fileUrl(entity.getFileUrl())
                .submittedAt(entity.getSubmittedAt())
                .build();
    }
}
