package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.admin.CourseCreateDTO;
import com.flic.courseRegister.dto.admin.CourseUpdateDTO;
import com.flic.courseRegister.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseCreateMapper {
    public Course toEntity(CourseCreateDTO dto) {
        return Course.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .duration(dto.getDuration())
                .level(dto.getLevel())
                .isPublished(false)      // mặc định nháp
                .status("draft")
                .build();
    }

    public void updateEntity(Course course, CourseUpdateDTO dto) {
        if (dto.getTitle() != null)       course.setTitle(dto.getTitle());
        if (dto.getDescription() != null) course.setDescription(dto.getDescription());
        if (dto.getPrice() != null)       course.setPrice(dto.getPrice());
        if (dto.getDuration() != null)    course.setDuration(dto.getDuration());
        if (dto.getLevel() != null)       course.setLevel(dto.getLevel());
        if (dto.getIsPublished() != null) {
            course.setIsPublished(dto.getIsPublished());
            course.setStatus(dto.getIsPublished() ? "active" : "inactive");
        }
    }
}
