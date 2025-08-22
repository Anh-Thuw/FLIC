package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.CourseProgressDTO;
import com.flic.courseRegister.service.user.CourseProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class MyProgressController {

    private final CourseProgressService service;

    // Tất cả khóa đã enroll
    @GetMapping("/progress")
    public List<CourseProgressDTO> myCoursesProgress(
            @RequestParam(defaultValue = "0.7") double wAssignment,
            @RequestParam(defaultValue = "0.3") double wAttendance) {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        var principal = (com.flic.courseRegister.security.UserDetailsImpl) auth.getPrincipal();
        Long userId = principal.getUser().getId();

        return service.getMyCoursesProgress(userId, wAssignment, wAttendance);
    }

    // Một khóa cụ thể
    @GetMapping(value="/progress", params="courseId")
    public CourseProgressDTO myCourseProgress(
            @RequestParam Long courseId,
            @RequestParam(defaultValue="0.7") double wAssignment,
            @RequestParam(defaultValue="0.3") double wAttendance) {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        var principal = (com.flic.courseRegister.security.UserDetailsImpl) auth.getPrincipal();
        Long userId = principal.getUser().getId();

        return service.getMyCourseProgress(userId, courseId, wAssignment, wAttendance);
    }
}

