package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.CourseInstructor;
import com.flic.courseRegister.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseInstructorRepository extends JpaRepository<CourseInstructor, Long> {
    List<CourseInstructor> findByUserEmail(String email);
    Optional<CourseInstructor> findFirstByCourseId(Long courseId);

    CourseInstructor findByCourseId(Long id);
    boolean existsByUserAndCourse(User user, Course course);
}
