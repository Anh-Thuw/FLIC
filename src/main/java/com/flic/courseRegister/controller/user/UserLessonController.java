package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.AssignmentViewDTO;
import com.flic.courseRegister.service.user.UserAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class UserLessonController {
    private final UserAssignmentService userAssignmentService;

    @GetMapping("/{lessonId}/assignments")
    public List<AssignmentViewDTO> list(@PathVariable Long lessonId) {
        return userAssignmentService.getAssignmentsByLessonId(lessonId);
    }
}
