package com.flic.courseRegister.mapper.lecture;

import com.flic.courseRegister.dto.lecture.ListStudentsLessonViewDTO;
import com.flic.courseRegister.dto.lecture.StudentAttendanceDTO;
import com.flic.courseRegister.entity.Attendance;
import org.springframework.stereotype.Component;

@Component
public class ListStudentsLessonMapper {
    public ListStudentsLessonViewDTO toDto(Attendance attendance){
        return ListStudentsLessonViewDTO.builder()
                .studentId(attendance.getEnrollment().getUser().getStudentId())
                .fullName(attendance.getEnrollment().getUser().getFullName())
                .build();
    }
}
