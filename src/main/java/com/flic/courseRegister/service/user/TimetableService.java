package com.flic.courseRegister.service.user;

import com.flic.courseRegister.dto.user.LessonTimetableDTO;
import java.util.List;

public interface TimetableService {
    List<LessonTimetableDTO> getTimetable();
}
