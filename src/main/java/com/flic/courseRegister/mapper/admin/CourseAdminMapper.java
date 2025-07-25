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
                .description(course.getDescription())
                .rating(course.getRating())
                .price(course.getPrice())
                .duration(course.getDuration())
                .status(course.getStatus())
                .image(course.getImage())
                .startMonth(course.getStartMonth())
                .type(course.getType())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }
}

