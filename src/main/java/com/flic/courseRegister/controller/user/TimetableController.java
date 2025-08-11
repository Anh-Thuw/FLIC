package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.LessonTimetableDTO;
import com.flic.courseRegister.service.user.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/timetable")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @GetMapping
    public List<LessonTimetableDTO> getTimetable() {
        return timetableService.getTimetable();
    }

}
