package com.flic.courseRegister.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flic.courseRegister.dto.user.EnrollmentRequest;
import com.flic.courseRegister.dto.user.EnrollmentResponse;
import com.flic.courseRegister.service.user.EnrollmentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EnrollmentResponse> enroll(
            @RequestParam("data") String data,
            @RequestPart(value = "file", required = false) MultipartFile file) throws JsonProcessingException {

        EnrollmentRequest request = new ObjectMapper().readValue(data, EnrollmentRequest.class);
        EnrollmentResponse response = enrollmentService.enroll(request, file);
        return ResponseEntity.ok(response);
    }

}


