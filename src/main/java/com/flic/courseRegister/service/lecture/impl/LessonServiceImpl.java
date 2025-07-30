package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.LessonCreateDTO;
import com.flic.courseRegister.dto.lecture.LessonViewDTO;
import com.flic.courseRegister.entity.*;
import com.flic.courseRegister.mapper.lecture.LessonMapper;
import com.flic.courseRegister.repository.*;
import com.flic.courseRegister.service.lecture.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final LessonMapper lessonMapper;
    @Override
    public LessonViewDTO createLesson(LessonCreateDTO lessonCreateDTO, String email) {
        Course course = courseRepository.findById(lessonCreateDTO.getCourseId())
                .orElseThrow(()-> new RuntimeException(("Không tìm thấy khóa học")));
        User creator = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy người dùng"));
        Lesson newLesson = lessonMapper.toEntity(lessonCreateDTO,course,creator);
        lessonRepository.save(newLesson);

        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(course.getId());
        List<Attendance> attendanceList = enrollments.stream().map(
                enrollment -> Attendance.builder()
                        .lesson(newLesson)
                        .enrollment(enrollment)
                        .status(Attendance.Status.absent)
                        .build()).toList();
        attendanceRepository.saveAll(attendanceList);
        return lessonMapper.toDto(newLesson);
    }
}
