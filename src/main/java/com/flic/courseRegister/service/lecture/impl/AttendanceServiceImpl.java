package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.StudentAttendanceDTO;
import com.flic.courseRegister.mapper.lecture.StudentAttendanceMapper;
import com.flic.courseRegister.repository.AttendanceRepository;
import com.flic.courseRegister.service.lecture.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentAttendanceMapper studentAttendanceMapper;

    @Override
    public List<StudentAttendanceDTO> getStudentsByLesson(Long lessonId) {
        return attendanceRepository.findByLessonId(lessonId)
                .stream()
                .map(studentAttendanceMapper::toDto)
                .toList();
    }
}
