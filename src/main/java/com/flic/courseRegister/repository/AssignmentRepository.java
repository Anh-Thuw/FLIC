package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Assignment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByLessonId(Long lessonId);
    @EntityGraph(attributePaths = {"lesson", "lesson.course"})
    Optional<Assignment> findWithLessonAndCourseById(Long id);
}
