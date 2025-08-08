package com.flic.courseRegister.controller.user;

import com.flic.courseRegister.dto.user.CourseDetailResponse;
import com.flic.courseRegister.dto.user.CourseResponse;
import com.flic.courseRegister.repository.CourseRepository;
import com.flic.courseRegister.service.user.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseResponse> getCourses() {
        return courseService.getAllActiveCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDetailResponse> getCourseDetail(@PathVariable Long id) {
        CourseDetailResponse response = courseService.getCourseDetailById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/registered")
    public ResponseEntity<List<CourseDetailResponse>> getCourseRegisteredByUser(Authentication authentication){
        String email = authentication.getName();
        List<CourseDetailResponse> courseDetaiList = courseService.getCourseByUserEmail(email);
        return ResponseEntity.ok(courseDetaiList);
    }
}