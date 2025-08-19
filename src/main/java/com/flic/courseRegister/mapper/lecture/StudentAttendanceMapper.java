package com.flic.courseRegister.mapper.lecture;

import com.flic.courseRegister.dto.lecture.StudentAttendanceDTO;
import com.flic.courseRegister.entity.Attendance;
import org.springframework.stereotype.Component;

@Component
public class StudentAttendanceMapper {
    public StudentAttendanceDTO toDto(Attendance attendance){
        return StudentAttendanceDTO.builder()
                .birthDate(attendance.getEnrollment().getUser().getBirthDate())
                .fullName(attendance.getEnrollment().getUser().getFullName())
                .status(attendance.getStatus())
                .build();
    }
}
