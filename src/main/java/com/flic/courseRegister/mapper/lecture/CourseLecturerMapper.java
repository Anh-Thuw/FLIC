package com.flic.courseRegister.mapper.lecture;

import com.flic.courseRegister.dto.lecture.CourseLecturerViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.InstructorProfile;
import com.flic.courseRegister.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CourseLecturerMapper {
    public CourseLecturerViewDTO toDto(Course course, User user){
        return CourseLecturerViewDTO.builder()
                .title(course.getTitle())
                .lecturerInCharge(user.getFullName())
                .description(course.getDescription())
                .price(course.getPrice())
                .status(course.getStatus())
                .build();
    }
}
