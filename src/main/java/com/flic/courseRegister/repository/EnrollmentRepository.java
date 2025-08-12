package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByCourseId(Long courseId);
    List<Enrollment> findByUserEmail(String email);
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
    @Query("""
        select e.id
        from Enrollment e
        join e.course c
        join Assignment a on a.lesson.course.id = c.id
        where a.id = :assignmentId
          and e.user.id = :userId
    """)
    Optional<Long> findEnrollmentIdByAssignmentIdAndUserId(@Param("assignmentId") Long assignmentId,
                                                           @Param("userId") Long userId);
}
