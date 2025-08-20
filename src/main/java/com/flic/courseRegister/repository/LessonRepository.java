package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCourseId(Long courseId);

//    @Query("""
//        SELECT l FROM Lesson l
//        JOIN Enrollment e ON e.course.id = l.course.id
//        WHERE e.user.id = :userId
//        ORDER BY l.weekIndex, l.sessionIndex
//    """)


    @Query("""
        SELECT l FROM Lesson l
        JOIN Enrollment e ON e.course.id = l.course.id
        WHERE e.user.id = :userId
        ORDER BY l.weekIndex, l.plannedAt
    """)
    List<Lesson> findLessonsByUserId(@Param("userId") Long userId);

    @Query("""
    SELECT l FROM Lesson l
    WHERE l.creatorId.id = :userId
    ORDER BY l.weekIndex, l.plannedAt
""")
    List<Lesson> findLessonsByCreatorId(@Param("userId") Long userId);

}
