package com.flic.courseRegister.mapper.user;

import com.flic.courseRegister.dto.user.LessonTimetableDTO;
import com.flic.courseRegister.entity.Lesson;
import com.flic.courseRegister.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TimetableMapper {

    @Mapping(target = "courseTitle", source = "course.title")
    @Mapping(target = "lecturerName", source = "course", qualifiedByName = "mapInstructorName")
    @Mapping(target = "dayOfWeek", source = "weekIndex", qualifiedByName = "mapDayOfWeek")
    @Mapping(target = "startTime", source = "sessionIndex", qualifiedByName = "mapStartTime")
    @Mapping(target = "endTime", source = "sessionIndex", qualifiedByName = "mapEndTime")
    LessonTimetableDTO toDto(Lesson lesson);

    @Named("mapInstructorName")
    default String getLecturerName(com.flic.courseRegister.entity.Course course) {
        return course.getInstructors().stream()
                .map(ci -> ci.getUser().getFullName())
                .findFirst().orElse("Chưa phân công");
    }

    @Named("mapDayOfWeek")
    default String mapDayOfWeek(int weekIndex) {
        return switch (weekIndex % 5) {
            case 0 -> "Thứ 2";
            case 1 -> "Thứ 3";
            case 2 -> "Thứ 4";
            case 3 -> "Thứ 5";
            case 4 -> "Thứ 6";
            default -> "Không rõ";
        };
    }

    @Named("mapStartTime")
    default String mapStartTime(int sessionIndex) {
        return sessionIndex == 1 ? "07:30" : "13:30";
    }

    @Named("mapEndTime")
    default String mapEndTime(int sessionIndex) {
        return sessionIndex == 1 ? "11:00" : "17:00";
    }

}
