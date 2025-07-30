package com.flic.courseRegister.mapper.lecture;

import com.flic.courseRegister.dto.lecture.LessonCreateDTO;
import com.flic.courseRegister.dto.lecture.LessonViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.Lesson;
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
                .build();
    }
    public LessonViewDTO toDto(Lesson lesson){
        return LessonViewDTO.builder()
                .title(lesson.getTitle())
                .description(lesson.getDescription())
                .session_index(lesson.getSessionIndex())
                .week_index(lesson.getWeekIndex())
                .build();
    }
}
