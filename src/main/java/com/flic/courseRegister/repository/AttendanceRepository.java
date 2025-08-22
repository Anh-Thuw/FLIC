package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Attendance;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByLessonId(Long lessonId);
    @Transactional
    @Modifying
    void deleteByLessonId(Long lessonId);
}
