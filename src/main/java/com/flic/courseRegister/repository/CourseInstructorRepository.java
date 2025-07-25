package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.CourseInstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseInstructorRepository extends JpaRepository<CourseInstructor, Long> {
    List<CourseInstructor> findByUserEmail(String email);
}
