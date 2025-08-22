package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.LessonCreateDTO;
import com.flic.courseRegister.dto.lecture.LessonUpdateDTO;
import com.flic.courseRegister.dto.lecture.LessonViewDTO;
import com.flic.courseRegister.entity.*;
import com.flic.courseRegister.mapper.lecture.LessonMapper;
import com.flic.courseRegister.repository.*;
import com.flic.courseRegister.service.lecture.LessonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final LessonMapper lessonMapper;
    private final LessonRevisionRepository lessonRevisionRepository;
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
                        .status(Attendance.Status.present)
                        .build()).toList();
        attendanceRepository.saveAll(attendanceList);
        return lessonMapper.toDto(newLesson);
    }

    @Override
    public LessonViewDTO updateLesson(LessonUpdateDTO dto, Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson with id " + lessonId + " not found"));

        // cập nhật các trường từ DTO
        if (dto.getTitle() != null) {
            lesson.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            lesson.setDescription(dto.getDescription());
        }
        if (dto.getWeekIndex() != null) {
            lesson.setWeekIndex(dto.getWeekIndex());
        }
        if (dto.getPlannedAt() != null) {
            lesson.setPlannedAt(dto.getPlannedAt());
        }
        if (dto.getEndTime() != null) {
            lesson.setEndTime(dto.getEndTime());
        }
        Lesson saved = lessonRepository.save(lesson);
        return lessonMapper.toDto(saved);
    }

    @Override
    public List<LessonViewDTO> getLessonByCourseId(Long courseId) {
        return lessonRepository.findByCourseId(courseId)
                .stream()
                .map(lessonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteLesson(Long lessonId) {
        if (!lessonRepository.existsById(lessonId)) {
            throw new RuntimeException("Lesson with id " + lessonId + " not found");
        }
        // Xóa hết attendance của lesson này
        attendanceRepository.deleteByLessonId(lessonId);
        lessonRepository.deleteById(lessonId);
    }
    }

