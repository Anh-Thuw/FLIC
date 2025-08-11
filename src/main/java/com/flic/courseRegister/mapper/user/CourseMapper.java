package com.flic.courseRegister.mapper.user;

import com.flic.courseRegister.dto.user.CourseDetailResponse;
import com.flic.courseRegister.dto.user.CourseResponse;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.CourseInstructor;
import com.flic.courseRegister.entity.InstructorProfile;
import com.flic.courseRegister.entity.User;
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
        dto.setDescription(course.getDescription());
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

    public CourseDetailResponse toDetailDto(Course course, CourseInstructor courseInstructor) {
        if (course == null) return null;

        CourseDetailResponse dto = new CourseDetailResponse();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setRating(course.getRating());
        dto.setDescription(course.getDescription());
        dto.setPrice(course.getPrice());
        dto.setDuration(course.getDuration());
        dto.setStatus(course.getStatus());
        dto.setImageUrl(course.getImage());
        dto.setStartMonth(course.getStartMonth());
        dto.setSchedule(course.getSchedule());
        dto.setType(String.valueOf(course.getType()));
        dto.setLecturerName(courseInstructor.getUser().getFullName());

        return dto;
    }
}
