package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByLessonId(Long lessonId);
}
