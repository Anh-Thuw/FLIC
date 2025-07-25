package com.flic.courseRegister.controller.lecture;

import com.flic.courseRegister.dto.lecture.StudentAttendanceDTO;
import com.flic.courseRegister.service.lecture.AttendanceService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentAttendanceDTO>> getStudentsByLesson(@RequestParam Long lessonId){
        return ResponseEntity.ok(attendanceService.getStudentsByLesson(lessonId));
    }
}
