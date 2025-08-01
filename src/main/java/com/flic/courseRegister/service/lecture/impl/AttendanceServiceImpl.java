package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.ListStudentsLessonViewDTO;
import com.flic.courseRegister.dto.lecture.StudentAttendanceDTO;
import com.flic.courseRegister.dto.lecture.StudentsAttendanceUpdateDTO;
import com.flic.courseRegister.entity.Attendance;
import com.flic.courseRegister.mapper.lecture.ListStudentsLessonMapper;
import com.flic.courseRegister.mapper.lecture.StudentAttendanceMapper;
import com.flic.courseRegister.repository.AttendanceRepository;
import com.flic.courseRegister.service.lecture.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentAttendanceMapper studentAttendanceMapper;
    private final ListStudentsLessonMapper listStudentsLessonMapper;
    @Override
    public List<StudentAttendanceDTO> getStudentsStatusByLesson(Long lessonId) {
        return attendanceRepository.findByLessonId(lessonId)
                .stream()
                .map(studentAttendanceMapper::toDto)
                .toList();
    }

    @Override
    public List<ListStudentsLessonViewDTO> getStudentsByLesson(Long lessonId) {
        return attendanceRepository.findByLessonId(lessonId)
                .stream()
                .map(listStudentsLessonMapper::toDto)
                .toList();
    }

    @Override
    public List<StudentAttendanceDTO> updateStatus(Long lessonId, List<StudentsAttendanceUpdateDTO> dtos) {
        List<StudentAttendanceDTO> results = new ArrayList<>();

        for (StudentsAttendanceUpdateDTO dto : dtos) {
            Attendance attendance = attendanceRepository.findById(dto.getAttendanceId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy bản ghi điểm danh"));

            attendance.setStatus(dto.getStatus());
            Attendance saved = attendanceRepository.save(attendance);
            results.add(studentAttendanceMapper.toDto(saved));
        }

        return results;
    }
}
