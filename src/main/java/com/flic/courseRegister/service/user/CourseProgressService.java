package com.flic.courseRegister.service.user;

import com.flic.courseRegister.dto.user.CourseProgressDTO;

import java.util.List;

public interface CourseProgressService {
    List<CourseProgressDTO> getMyCoursesProgress(Long userId, double wAssignment, double wAttendance);
    CourseProgressDTO getMyCourseProgress(Long userId, Long courseId, double wAssignment, double wAttendance);
}

