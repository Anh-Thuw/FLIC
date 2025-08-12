package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    Optional<Submission> findFirstByAssignmentIdAndEnrollmentId(Long assignmentId, Long enrollmentId);
}
