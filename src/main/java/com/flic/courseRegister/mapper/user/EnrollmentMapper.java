package com.flic.courseRegister.mapper.user;

import com.flic.courseRegister.dto.user.EnrollmentResponse;
import com.flic.courseRegister.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "course.id", target = "courseId")
    EnrollmentResponse toDto(Enrollment enrollment);
}

