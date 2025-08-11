package com.flic.courseRegister.mapper.user;

import com.flic.courseRegister.dto.user.ScheduleViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {
    public ScheduleViewDTO toDto(User user, Course course){
        return ScheduleViewDTO.builder()
                .userId(user.getId())
                .courseTitle(course.getTitle())
                .schedule(course.getSchedule())
                .build();
    }
}
