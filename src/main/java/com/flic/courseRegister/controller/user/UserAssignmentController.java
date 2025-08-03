package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.AssignmentViewDTO;
import com.flic.courseRegister.service.user.UserAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class UserAssignmentController {

    private final UserAssignmentService userAssignmentService;

    @GetMapping("/{lessonId}")
    public ResponseEntity<List<AssignmentViewDTO>> getAssignmentsByLesson(@PathVariable Long lessonId) {
        List<AssignmentViewDTO> assignments = userAssignmentService.getAssignmentsByLessonId(lessonId);
        return ResponseEntity.ok(assignments);
    }
}

