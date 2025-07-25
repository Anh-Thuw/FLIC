package com.flic.courseRegister.controller.lecture;

import com.flic.courseRegister.dto.lecture.CourseLecturerViewDTO;
import com.flic.courseRegister.service.lecture.CourseLecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/lecturer")
@RequiredArgsConstructor
public class CourseLecturerController {
    private final CourseLecturerService courseLecturerService;
    @GetMapping("/course")
    public ResponseEntity<List<CourseLecturerViewDTO>> getLecturerCourse(Authentication authentication){
        String email = authentication.getName();
        List<CourseLecturerViewDTO> courses = courseLecturerService.getCourseByLecturerEmail(email);
        return ResponseEntity.ok(courses);
    }
}
