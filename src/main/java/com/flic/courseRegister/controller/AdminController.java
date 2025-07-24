package com.flic.courseRegister.controller;

import com.flic.courseRegister.dto.CourseAdminViewDTO;
import com.flic.courseRegister.dto.UserAdminViewDTO;
import com.flic.courseRegister.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")   // Cần cấu hình Spring Security
public class AdminController {

    private final AdminService service;

//    Lấy danh sách người dùng
    @GetMapping("/users")
    public ResponseEntity<Page<UserAdminViewDTO>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String status) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(service.getAllUsers(pageable, status));
    }
    // Xem ds courses
    @GetMapping("/courses")
    public ResponseEntity<List<CourseAdminViewDTO>> getCourses(
            @RequestParam(required = false) String status) {

        return ResponseEntity.ok(service.getAllCourses(status));
    }
}
