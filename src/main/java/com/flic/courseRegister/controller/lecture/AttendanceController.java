package com.flic.courseRegister.controller.lecture;

import com.flic.courseRegister.dto.lecture.ApiResponse;
import com.flic.courseRegister.dto.lecture.ListStudentsLessonViewDTO;
import com.flic.courseRegister.dto.lecture.StudentAttendanceDTO;
import com.flic.courseRegister.dto.lecture.StudentAttendanceUpdateListDTO;
import com.flic.courseRegister.service.lecture.AttendanceService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping("/attendance")
    public ResponseEntity<List<StudentAttendanceDTO>> getStudentsStatusByLesson(@RequestParam Long lessonId){
        return ResponseEntity.ok(attendanceService.getStudentsStatusByLesson(lessonId));
    }

    @GetMapping("/students")
    public ResponseEntity<List<ListStudentsLessonViewDTO>> getStudentsByLesson(@RequestParam Long lessonId){
        return ResponseEntity.ok(attendanceService.getStudentsByLesson(lessonId));
    }

    @PutMapping("/attendance/update-list")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<ApiResponse<List<StudentAttendanceDTO>>> updateAttendanceStatus(
            @RequestBody StudentAttendanceUpdateListDTO request) {
    try {
        List<StudentAttendanceDTO> updatedList = attendanceService.updateStatus(request);
        return ResponseEntity.ok(
                new ApiResponse<>("Cập nhật điểm danh thành công", updatedList)
        );
    }catch (Exception e){
        e.printStackTrace();
        return ResponseEntity.badRequest().body(
                new ApiResponse<>("Cập nhật điểm danh thất bại: " + e.getMessage(), null)
        );
    }
    }

}
