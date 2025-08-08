package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByCourseId(Long courseId);
    List<Enrollment> findByUserEmail(String email);
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}
