package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.SubmissionRequestDTO;
import com.flic.courseRegister.dto.user.SubmissionViewDTO;
import com.flic.courseRegister.service.user.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<SubmissionViewDTO> submitAssignment(@RequestBody SubmissionRequestDTO dto) {
        SubmissionViewDTO result = submissionService.submitAssignment(dto);
        return ResponseEntity.ok(result);
    }
}
