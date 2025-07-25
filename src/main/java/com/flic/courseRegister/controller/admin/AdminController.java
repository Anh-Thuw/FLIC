package com.flic.courseRegister.controller.admin;

import com.flic.courseRegister.dto.admin.*;
import com.flic.courseRegister.service.admin.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")   // Cần cấu hình Spring Security
public class AdminController {

    private final AdminService service;

//  Lấy danh sách người dùng
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

    @PostMapping("/courses")
    public ResponseEntity<ApiMessage> createCourse(
            @Valid @RequestBody CourseCreateDTO dto) {

        Long id = service.createCourse(dto);
        return ResponseEntity.ok(new ApiMessage("Tạo khoá học thành công, course_id = " + id));
    }

    /* PUT /courses/{id} – cập nhật */
    @PutMapping("/courses/{id}")
    public ResponseEntity<ApiMessage> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseUpdateDTO dto) {

        service.updateCourse(id, dto);
        return ResponseEntity.ok(new ApiMessage("Cập nhật khoá học thành công"));
    }

    /* DELETE /courses/{id} – xoá */
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<ApiMessage> deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
        return ResponseEntity.ok(new ApiMessage("Xoá khoá học thành công"));
    }
}
