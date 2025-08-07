package com.flic.courseRegister.controller.lecture;

import com.flic.courseRegister.dto.lecture.ApiResponse;
import com.flic.courseRegister.dto.lecture.AssignmentCreateDTO;
import com.flic.courseRegister.dto.lecture.AssignmentViewDTO;
import com.flic.courseRegister.service.lecture.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lecturer/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;
    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<ApiResponse<AssignmentViewDTO>> createAssignment(@RequestBody AssignmentCreateDTO assignmentCreateDTO){
        AssignmentViewDTO assignment = assignmentService.createAssignment(assignmentCreateDTO);
        return ResponseEntity.ok(new ApiResponse<>("Tạo bài tập thành công!",assignment));
    }
    @GetMapping("/lesson")
    public ResponseEntity<List<AssignmentViewDTO>> getAssignmentByLesson(@RequestParam Long lessonId){
        return ResponseEntity.ok(assignmentService.getAssignmentByLessonId(lessonId));
    }
}
