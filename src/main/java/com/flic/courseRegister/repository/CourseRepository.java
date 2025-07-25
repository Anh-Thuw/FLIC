package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructorEmail(String email);
}
