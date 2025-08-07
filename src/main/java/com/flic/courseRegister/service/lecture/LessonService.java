package com.flic.courseRegister.service.lecture;

import com.flic.courseRegister.dto.lecture.LessonCreateDTO;
import com.flic.courseRegister.dto.lecture.LessonUpdateDTO;
import com.flic.courseRegister.dto.lecture.LessonViewDTO;

import java.util.List;

public interface LessonService {
    LessonViewDTO createLesson(LessonCreateDTO lessonCreateDTO, String email);
    LessonUpdateDTO updateLesson(LessonUpdateDTO lessonUpdateDTO, String email);
    List<LessonViewDTO> getLessonByCourseId(Long courseId);
}
