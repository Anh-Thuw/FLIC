package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Assignment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByLessonId(Long lessonId);
    @EntityGraph(attributePaths = {"lesson", "lesson.course"})
    Optional<Assignment> findWithLessonAndCourseById(Long id);
    @Query("""
      select a
      from Assignment a
      join a.lesson l
      where l.course.id = :courseId
      order by a.dueDate asc nulls last
    """)
    List<Assignment> findByCourseId(@Param("courseId") Long courseId);
}
