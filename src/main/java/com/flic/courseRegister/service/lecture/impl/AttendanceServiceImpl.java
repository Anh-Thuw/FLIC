package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.ListStudentsLessonViewDTO;
import com.flic.courseRegister.dto.lecture.StudentAttendanceDTO;
import com.flic.courseRegister.dto.lecture.StudentAttendanceUpdateListDTO;
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
    public List<StudentAttendanceDTO> updateStatus(StudentAttendanceUpdateListDTO request) {
        Long lessonId = request.getLessonId();
        List<StudentsAttendanceUpdateDTO> dtos = request.getUpdates();
        List<StudentAttendanceDTO> results = new ArrayList<>();

        List<Attendance> attendances = attendanceRepository.findByLessonId(lessonId);

        if (attendances.size() != dtos.size()) {
            throw new RuntimeException("Số lượng attendance trong DB (" + attendances.size() +
                    ") không khớp với số lượng updates gửi lên (" + dtos.size() + ")");
        }

        for (int i = 0; i < dtos.size(); i++) {
            StudentsAttendanceUpdateDTO dto = dtos.get(i);
            Attendance attendance = attendances.get(i); // map theo index

            attendance.setStatus(dto.getStatus());
            Attendance saved = attendanceRepository.save(attendance);
            results.add(studentAttendanceMapper.toDto(saved));
        }

        return results;
    }
}
