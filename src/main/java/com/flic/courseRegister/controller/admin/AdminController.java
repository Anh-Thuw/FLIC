package com.flic.courseRegister.controller.admin;

import com.flic.courseRegister.dto.admin.*;
import com.flic.courseRegister.dto.user.UserCreateDTO;
import com.flic.courseRegister.dto.user.UserUpdateDTO;
import com.flic.courseRegister.entity.Course;
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
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String keyword) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(service.getAllUsers(pageable, status, role, keyword));
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UserAdminViewDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PostMapping("/lecturer")
    public ResponseEntity<ApiMessage> createUser(@Valid @RequestBody UserCreateDTO dto) {
        Long id = service.createUser(dto);
        return ResponseEntity.ok(new ApiMessage("Tạo giảng viên thành công, ID: " + id));
    }

    @PutMapping("/lecturer/{id}")
    public ResponseEntity<ApiMessage> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateDTO dto) {
        service.updateUser(id, dto);
        return ResponseEntity.ok(new ApiMessage("Cập nhật người dùng thành công"));
    }

    @DeleteMapping("/lecturer/{id}")
    public ResponseEntity<ApiMessage> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.ok(new ApiMessage("Xóa người dùng thành công"));
    }
    // Xem ds courses
    @GetMapping("/courses")
    public ResponseEntity<List<CourseAdminViewDTO>> getCourses(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Course.CourseType type,
            @RequestParam(required = false) String startMonth,
            @RequestParam(required = false) String keyword) {

        return ResponseEntity.ok(service.getAllCourses(status, type, startMonth, keyword));
    }

    @PostMapping("/courses")
    public ResponseEntity<ApiMessage> createCourse(
            @Valid @RequestBody CourseCreateDTO dto) {

        Long id = service.createCourse(dto);
        return ResponseEntity.ok(new ApiMessage("Tạo khoá học thành công, course_id = " + id));
    }
    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseAdminViewDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCourseById(id));
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
