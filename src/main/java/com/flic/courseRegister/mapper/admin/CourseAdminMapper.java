package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.admin.CourseAdminViewDTO;
import com.flic.courseRegister.dto.admin.CourseCreateDTO;
import com.flic.courseRegister.dto.admin.CourseUpdateDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CourseAdminMapper {
    public CourseAdminViewDTO toDto(Course course) {
        User lecturer = null;

        if (course.getInstructors() != null && !course.getInstructors().isEmpty()) {
            lecturer = course.getInstructors().get(0).getUser();
        }
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
                .nameLecturer(lecturer != null ? lecturer.getFullName() : null)
                .emailLecturer(lecturer != null ? lecturer.getEmail() : null)
                .build();
    }
}

