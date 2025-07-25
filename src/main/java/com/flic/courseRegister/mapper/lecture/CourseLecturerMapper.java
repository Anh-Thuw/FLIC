package com.flic.courseRegister.mapper.lecture;

import com.flic.courseRegister.dto.lecture.CourseLecturerViewDTO;
import com.flic.courseRegister.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseLecturerMapper {
    public CourseLecturerViewDTO toDto(Course course){
        return CourseLecturerViewDTO.builder()
                .title(course.getTitle())
                .description(course.getDescription())
                .thumbnailUrl(course.getThumbnailUrl())
                .level(course.getLevel())
                .slug(course.getSlug())
                .price(course.getPrice())
                .status(course.getStatus())
                .build();
    }
}
