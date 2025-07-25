package com.flic.courseRegister.service.lecture;

import com.flic.courseRegister.dto.lecture.StudentAttendanceDTO;

import java.util.List;

public interface AttendanceService {
    List<StudentAttendanceDTO> getStudentsByLesson(Long lessonId);
}
