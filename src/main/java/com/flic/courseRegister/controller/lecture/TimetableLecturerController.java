package com.flic.courseRegister.controller.lecture;

import com.flic.courseRegister.dto.user.LessonTimetableDTO;
import com.flic.courseRegister.service.user.TimetableService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/timetable-lecturer")
@AllArgsConstructor
public class TimetableLecturerController {
    private final TimetableService timetableService;

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping
    public List<LessonTimetableDTO> getLecturerTimetable() {
        return timetableService.getCreatorLessons();
    }
}
