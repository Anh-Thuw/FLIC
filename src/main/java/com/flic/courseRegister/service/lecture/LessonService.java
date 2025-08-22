package com.flic.courseRegister.service.lecture;

import com.flic.courseRegister.dto.lecture.LessonCreateDTO;
import com.flic.courseRegister.dto.lecture.LessonUpdateDTO;
import com.flic.courseRegister.dto.lecture.LessonViewDTO;
import com.flic.courseRegister.entity.Lesson;

import java.util.List;

public interface LessonService {
    LessonViewDTO createLesson(LessonCreateDTO lessonCreateDTO, String email);
    LessonViewDTO  updateLesson(LessonUpdateDTO dto, Long lessonId);
    List<LessonViewDTO> getLessonByCourseId(Long courseId);
    void deleteLesson(Long lessonId);
}
