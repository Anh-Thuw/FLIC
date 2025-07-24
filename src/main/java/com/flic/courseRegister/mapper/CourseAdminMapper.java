package com.flic.courseRegister.mapper;

import com.flic.courseRegister.dto.CourseAdminViewDTO;
import com.flic.courseRegister.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseAdminMapper {
    public CourseAdminViewDTO toDto(Course course) {
        return CourseAdminViewDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .status(course.getStatus() != null ? course.getStatus() : course.getStatusFromPublished())
                .build();
    }
}
