package com.flic.courseRegister.mapper.user;

import com.flic.courseRegister.dto.user.LessonTimetableDTO;
import com.flic.courseRegister.entity.Lesson;
import com.flic.courseRegister.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface TimetableMapper {



    @Mapping(target = "id", source = "id")
    @Mapping(target = "courseTitle", source = "course.title")
    @Mapping(target = "lecturerName", source = "course", qualifiedByName = "mapInstructorName")
    @Mapping(target = "weekIndex", source = "lesson", qualifiedByName = "mapWeekIndex")
    @Mapping(target = "dayOfWeek", source = "plannedAt", qualifiedByName = "mapDayOfWeek")
    @Mapping(target = "startTime", source = "plannedAt", qualifiedByName = "mapStartTime")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "mapEndTime")
    LessonTimetableDTO toDto(Lesson lesson);



    @Named("mapInstructorName")
    default String getLecturerName(com.flic.courseRegister.entity.Course course) {
        return course.getInstructors().stream()
                .map(ci -> ci.getUser().getFullName())
                .findFirst().orElse("Chưa phân công");
    }

    @Named("mapWeekIndex")
    default String mapWeekIndex(Lesson lesson) {
        Integer weekIndex = lesson.getWeekIndex();
        LocalDateTime plannedAt = lesson.getPlannedAt();
        if (weekIndex == null || plannedAt == null) return null;

        LocalDateTime monday = plannedAt.minusDays(plannedAt.getDayOfWeek().getValue() - 1);
        LocalDateTime sunday = monday.plusDays(6);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM");

        return String.format("Tuần thứ %d: %s - %s",
                weekIndex,
                monday.format(fmt),
                sunday.format(fmt));
    }
    @Named("mapDayOfWeek")
    default String mapDayOfWeek(LocalDateTime plannedAt) {
        if (plannedAt == null) return null;
        return switch (plannedAt.getDayOfWeek()) {
            case MONDAY -> "Thứ Hai";
            case TUESDAY -> "Thứ Ba";
            case WEDNESDAY -> "Thứ Tư";
            case THURSDAY -> "Thứ Năm";
            case FRIDAY -> "Thứ Sáu";
            case SATURDAY -> "Thứ Bảy";
            case SUNDAY -> "Chủ Nhật";
        };
    }

    @Named("mapStartTime")
    default String mapStartTime(LocalDateTime plannedAt) {
        return plannedAt != null
                ? plannedAt.format(DateTimeFormatter.ofPattern("HH:mm"))
                : null;
    }

    @Named("mapEndTime")
    default String mapEndTime(LocalDateTime endTime) {
        return endTime != null
                ? endTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                : null;
    }








//    @Mapping(target = "courseTitle", source = "course.title")
//    @Mapping(target = "lecturerName", source = "course", qualifiedByName = "mapInstructorName")
//    @Mapping(target = "dayOfWeek", source = "weekIndex", qualifiedByName = "mapDayOfWeek")
//    @Mapping(target = "startTime", source = "sessionIndex", qualifiedByName = "mapStartTime")
//    @Mapping(target = "endTime", source = "sessionIndex", qualifiedByName = "mapEndTime")
//    LessonTimetableDTO toDto(Lesson lesson);
//
//    @Named("mapInstructorName")
//    default String getLecturerName(com.flic.courseRegister.entity.Course course) {
//        return course.getInstructors().stream()
//                .map(ci -> ci.getUser().getFullName())
//                .findFirst().orElse("Chưa phân công");
//    }
//
//    @Named("mapDayOfWeek")
//    default String mapDayOfWeek(int weekIndex) {
//        return switch (weekIndex % 5) {
//            case 0 -> "Thứ 2";
//            case 1 -> "Thứ 3";
//            case 2 -> "Thứ 4";
//            case 3 -> "Thứ 5";
//            case 4 -> "Thứ 6";
//            default -> "Không rõ";
//        };
//    }
//
//    @Named("mapStartTime")
//    default String mapStartTime(int sessionIndex) {
//        return switch (sessionIndex) {
//            case 1 -> "07:30";   // Buổi sáng
//            case 2 -> "13:30";   // Buổi chiều
//            case 3 -> "18:00";   // Buổi tối
//            default -> "Không rõ";
//        };
//    }
//
//    @Named("mapEndTime")
//    default String mapEndTime(int sessionIndex) {
//        return switch (sessionIndex) {
//            case 1 -> "11:00";   // Buổi sáng
//            case 2 -> "17:00";   // Buổi chiều
//            case 3 -> "21:00";   // Buổi tối
//            default -> "Không rõ";
//        };
//    }

}
