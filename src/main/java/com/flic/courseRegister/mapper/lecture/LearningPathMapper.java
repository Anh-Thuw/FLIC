package com.flic.courseRegister.mapper.lecture;

import com.flic.courseRegister.dto.lecture.LearningPathViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.LearningPath;
import org.springframework.stereotype.Component;

@Component
public class LearningPathMapper {
    public LearningPathViewDTO toDto(LearningPath learningPath){
        return LearningPathViewDTO.builder()
                .courseId(learningPath.getCourse().getId())
                .week(learningPath.getWeek())
                .session(learningPath.getSession())
                .title(learningPath.getTitle())
                .build();
    }
    public LearningPath toEntity(LearningPathViewDTO learningPathViewDTO, Course course){
        return LearningPath.builder()
                .course(course)
                .week(learningPathViewDTO.getWeek())
                .session(learningPathViewDTO.getSession())
                .title(learningPathViewDTO.getTitle())
                .build();
    }
}
