package com.flic.courseRegister.mapper.admin;

import com.flic.courseRegister.dto.admin.InstructorToCourseDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.CourseInstructor;
import com.flic.courseRegister.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstructorToCourseMapper {
    public CourseInstructor toEntity(User user, Course course){
        return CourseInstructor.builder()
                .user(user)
                .course(course)
                .build();
    }
}
