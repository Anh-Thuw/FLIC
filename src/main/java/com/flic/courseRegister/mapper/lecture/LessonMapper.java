package com.flic.courseRegister.mapper.lecture;

import com.flic.courseRegister.dto.lecture.LessonCreateDTO;
import com.flic.courseRegister.dto.lecture.LessonUpdateDTO;
import com.flic.courseRegister.dto.lecture.LessonViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.Lesson;
import com.flic.courseRegister.entity.LessonRevision;
import com.flic.courseRegister.entity.User;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    public Lesson toEntity(LessonCreateDTO lessonCreateDTO, Course course, User user){
        return Lesson.builder()
                .title(lessonCreateDTO.getTitle())
                .description(lessonCreateDTO.getDescription())
                .weekIndex(lessonCreateDTO.getWeek_index())
                .sessionIndex(lessonCreateDTO.getSession_index())
                .course(course)
                .creatorId(user)
                .build();
    }
    public LessonViewDTO toDto(Lesson lesson){
        return LessonViewDTO.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .description(lesson.getDescription())
                .session_index(lesson.getSessionIndex())
                .week_index(lesson.getWeekIndex())
                .build();
    }
    public LessonRevision updateEntity(Lesson lesson, LessonUpdateDTO dto, User editor) {
        return LessonRevision.builder()
                .lesson(lesson)
                .instructor(editor)
                .updatedTitle(dto.getUpdateTitle())
                .updatedDescription(dto.getUpdateDescription())
                .note(dto.getNote())
                .build();
    }
    public LessonUpdateDTO toEntityRevision(LessonRevision lessonRevision){
        return LessonUpdateDTO.builder()
                .lessonId(lessonRevision.getLesson().getId())
                .lecturerId(lessonRevision.getInstructor().getId())
                .updateTitle(lessonRevision.getUpdatedTitle())
                .updateDescription(lessonRevision.getUpdatedDescription())
                .note(lessonRevision.getNote())
                .build();
    }
}
