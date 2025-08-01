package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.AssignmentViewDTO;
import com.flic.courseRegister.service.user.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping("/{lessonId}")
    public ResponseEntity<List<AssignmentViewDTO>> getAssignmentsByLesson(@PathVariable Long lessonId) {
        List<AssignmentViewDTO> assignments = assignmentService.getAssignmentsByLessonId(lessonId);
        return ResponseEntity.ok(assignments);
    }
}

