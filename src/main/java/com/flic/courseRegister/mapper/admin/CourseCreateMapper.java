package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.admin.CourseCreateDTO;
import com.flic.courseRegister.dto.admin.CourseUpdateDTO;
import com.flic.courseRegister.entity.Course;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CourseCreateMapper {
    public Course toEntity(CourseCreateDTO dto) {
        return Course.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .duration(dto.getDuration())
                .image(dto.getImage())
                .startMonth(dto.getStartMonth())
                .type(dto.getType())
                .status("draft")  // Mặc định là draft
                .rating(BigDecimal.ZERO)  // Rating ban đầu là 0
                .build();
    }

    public void updateEntity(Course course, CourseUpdateDTO dto) {
        if (dto.getTitle() != null) course.setTitle(dto.getTitle());
        if (dto.getDescription() != null) course.setDescription(dto.getDescription());
        if (dto.getPrice() != null) course.setPrice(dto.getPrice());
        if (dto.getDuration() != null) course.setDuration(dto.getDuration());
        if (dto.getStatus() != null) course.setStatus(dto.getStatus());
        if (dto.getImage() != null) course.setImage(dto.getImage());
        if (dto.getStartMonth() != null) course.setStartMonth(dto.getStartMonth());
        if (dto.getType() != null) course.setType(dto.getType());
        if (dto.getRating() != null) course.setRating(dto.getRating());
    }
}

