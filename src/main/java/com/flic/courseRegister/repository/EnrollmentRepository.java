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

    @Query("""
  select e.id
  from Enrollment e
  where e.user.id = :userId
    and e.course.id = (select l.course.id from Lesson l where l.id = :lessonId)
""")
    Optional<Long> findEnrollmentIdByLessonIdAndUserId(@Param("lessonId") Long lessonId,
                                                       @Param("userId") Long userId);

    @Query(value = """
        SELECT
            e.id                            AS enrollmentId,
            e.course_id                     AS courseId,

            COUNT(DISTINCT a.id)            AS totalAssignments,
            COUNT(DISTINCT s.assignment_id) AS submittedCount,
            ROUND(
                COALESCE(
                    COUNT(DISTINCT s.assignment_id) / NULLIF(COUNT(DISTINCT a.id), 0),
                0) * 100, 2
            )                               AS assignmentsPercent,

            COUNT(DISTINCT l.id)            AS totalLessons,
            SUM(CASE WHEN att.status IN ('present','late','excused') THEN 1 ELSE 0 END)
                                            AS attendedCount,
            ROUND(
                COALESCE(
                    SUM(CASE WHEN att.status IN ('present','late','excused') THEN 1 ELSE 0 END)
                    / NULLIF(COUNT(DISTINCT l.id), 0),
                0) * 100, 2
            )                               AS attendancePercent

        FROM enrollment e
        LEFT JOIN lessons   l  ON l.course_id   = e.course_id
        LEFT JOIN assignment a  ON a.lesson_id  = l.id
        LEFT JOIN submission s  ON s.assignment_id = a.id
                               AND s.enrollment_id = e.id
        LEFT JOIN attendance att ON att.enrollment_id = e.id
                                AND att.lesson_id = l.id
        WHERE e.user_id = :userId
        GROUP BY e.id, e.course_id
        ORDER BY e.course_id
        """, nativeQuery = true)
    List<ProgressRow> fetchMyCoursesProgress(@Param("userId") Long userId);

    //cho 1 khóa cụ thể
    @Query(value = """
        SELECT
            e.id                            AS enrollmentId,
            e.course_id                     AS courseId,

            COUNT(DISTINCT a.id)            AS totalAssignments,
            COUNT(DISTINCT s.assignment_id) AS submittedCount,
            ROUND(
                COALESCE(
                    COUNT(DISTINCT s.assignment_id) / NULLIF(COUNT(DISTINCT a.id), 0),
                0) * 100, 2
            )                               AS assignmentsPercent,

            COUNT(DISTINCT l.id)            AS totalLessons,
            SUM(CASE WHEN att.status IN ('present','late','excused') THEN 1 ELSE 0 END)
                                            AS attendedCount,
            ROUND(
                COALESCE(
                    SUM(CASE WHEN att.status IN ('present','late','excused') THEN 1 ELSE 0 END)
                    / NULLIF(COUNT(DISTINCT l.id), 0),
                0) * 100, 2
            )                               AS attendancePercent

        FROM enrollment e
        LEFT JOIN lessons   l  ON l.course_id   = e.course_id
        LEFT JOIN assignment a  ON a.lesson_id  = l.id
        LEFT JOIN submission s  ON s.assignment_id = a.id
                               AND s.enrollment_id = e.id
        LEFT JOIN attendance att ON att.enrollment_id = e.id
                                AND att.lesson_id = l.id
        WHERE e.user_id = :userId AND e.course_id = :courseId
        GROUP BY e.id, e.course_id
        """, nativeQuery = true)
    ProgressRow fetchMyProgressForCourse(@Param("userId") Long userId, @Param("courseId") Long courseId);
}
