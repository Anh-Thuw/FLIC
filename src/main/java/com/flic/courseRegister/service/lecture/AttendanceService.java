package com.flic.courseRegister.service.lecture;

import com.flic.courseRegister.dto.lecture.ListStudentsLessonViewDTO;
import com.flic.courseRegister.dto.lecture.StudentAttendanceDTO;
import com.flic.courseRegister.dto.lecture.StudentsAttendanceUpdateDTO;

import java.util.List;

public interface AttendanceService {
    List<StudentAttendanceDTO> getStudentsStatusByLesson(Long lessonId);
    List<ListStudentsLessonViewDTO> getStudentsByLesson(Long lessonId);

    List<StudentAttendanceDTO> updateStatus(Long lessonId, List<StudentsAttendanceUpdateDTO> dtos);
}
