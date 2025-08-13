package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    Optional<Submission> findFirstByAssignmentIdAndEnrollmentId(Long assignmentId, Long enrollmentId);

    @Query("""
      select s.assignment.id
      from Submission s
      where s.enrollment.id = :enrollmentId
        and s.assignment.id in :assignmentIds
    """)
    Set<Long> findSubmittedAssignmentIds(@Param("enrollmentId") Long enrollmentId,
                                         @Param("assignmentIds") Collection<Long> assignmentIds);
}
