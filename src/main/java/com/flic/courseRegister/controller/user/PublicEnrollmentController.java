package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.EnrollmentResponse;
import com.flic.courseRegister.dto.user.PublicEnrollmentRequest;
import com.flic.courseRegister.service.user.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/enroll")
@RequiredArgsConstructor
public class PublicEnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponse> publicEnroll(@RequestBody PublicEnrollmentRequest request) {
        EnrollmentResponse response = enrollmentService.publicEnroll(request);
        return ResponseEntity.ok(response);
    }
}
