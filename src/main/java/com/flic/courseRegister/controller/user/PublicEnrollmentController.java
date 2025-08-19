package com.flic.courseRegister.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flic.courseRegister.dto.user.EnrollmentRequest;
import com.flic.courseRegister.dto.user.EnrollmentResponse;
import com.flic.courseRegister.dto.user.PublicEnrollmentRequest;
import com.flic.courseRegister.service.user.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/public/enroll")
@RequiredArgsConstructor
public class PublicEnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EnrollmentResponse> publicEnroll(@RequestParam("data") String data,
                                                           @RequestPart(value = "file", required = false) MultipartFile file) throws JsonProcessingException {
        PublicEnrollmentRequest request = new ObjectMapper().readValue(data, PublicEnrollmentRequest.class);
        EnrollmentResponse response = enrollmentService.publicEnroll(request, file);
        return ResponseEntity.ok(response);
    }
}