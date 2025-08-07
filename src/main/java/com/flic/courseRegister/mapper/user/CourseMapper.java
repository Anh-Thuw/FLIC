package com.flic.courseRegister.mapper.user;

import com.flic.courseRegister.dto.user.CourseDetailResponse;
import com.flic.courseRegister.dto.user.CourseResponse;
import com.flic.courseRegister.entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseResponse toDto(Course course) {
        if (course == null) return null;

        CourseResponse dto = new CourseResponse();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setRating(course.getRating());
        dto.setPrice(course.getPrice());
        dto.setStatus(course.getStatus());
        dto.setImageUrl(course.getImage());
        dto.setStartMonth(course.getStartMonth());
        dto.setType(String.valueOf(course.getType()));

        return dto;
    }

    public List<CourseResponse> toDtoList(List<Course> courses) {
        return courses.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CourseDetailResponse toDetailDto(Course course) {
        if (course == null) return null;

        CourseDetailResponse dto = new CourseDetailResponse();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setRating(course.getRating());
        dto.setPrice(course.getPrice());
        dto.setDuration(course.getDuration());
        dto.setStatus(course.getStatus());
        dto.setImageUrl(course.getImage());
        dto.setStartMonth(course.getStartMonth());
        dto.setType(String.valueOf(course.getType()));

        return dto;
    }
}
