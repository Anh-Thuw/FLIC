package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.admin.CourseAdminViewDTO;
import com.flic.courseRegister.dto.admin.CourseCreateDTO;
import com.flic.courseRegister.dto.admin.CourseUpdateDTO;
import com.flic.courseRegister.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseAdminMapper {
    public CourseAdminViewDTO toDto(Course course) {
        return CourseAdminViewDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .status(course.getStatus())
                .price(course.getPrice())
                .rating(course.getRating())
                .duration(course.getDuration())
                .startMonth(course.getStartMonth())
                .type(course.getType())
                .createdAt(course.getCreatedAt())
                .build();
    }
}

