package com.flic.courseRegister.controller.admin;

import com.flic.courseRegister.dto.admin.*;
import com.flic.courseRegister.dto.user.UserCreateDTO;
import com.flic.courseRegister.dto.user.UserUpdateDTO;
import com.flic.courseRegister.dto.user.UserViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.service.admin.AdminService;
import com.flic.courseRegister.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")   // Cần cấu hình Spring Security
@PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF')")
public class AdminController {

    private final AdminService service;

    @GetMapping("/users")
    public ResponseEntity<List<UserAdminViewDTO>> getUsers(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String keyword) {

        return ResponseEntity.ok(service.getAllUsers(status, role, keyword));
    }

    // Lấy danh sách giảng viên
    @GetMapping("/lecturers")
    public ResponseEntity<List<UserAdminViewDTO>> getTeachers(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String keyword) {

        return ResponseEntity.ok(service.getAllTeachers( status, keyword));
    }
    @PostMapping("/registerAccount")
    public ResponseEntity<ApiResponse<UserViewDTO>> createNewAccountRole(
            @RequestBody UserByRoleDTO userByRoleDTO
    ) {
        try {
            UserViewDTO newUser = service.createNewAccountRole(userByRoleDTO);
            ApiResponse<UserViewDTO> response = new ApiResponse<>(
                    true,
                    "Tạo mới tài khoản thành công!",
                    newUser
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            ApiResponse<UserViewDTO> response = new ApiResponse<>(
                    false,
                    "Tạo mới tài khoản thất bại: " + ex.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UserAdminViewDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    // Sửa thông tin user
    @PutMapping("/users/{id}")
    public ResponseEntity<ApiMessage> updateNormalUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateDTO dto) {
        service.updateUser(id, dto);
        return ResponseEntity.ok(new ApiMessage("Cập nhật người dùng thành công"));
    }

    // Xóa user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiMessage> deleteNormalUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.ok(new ApiMessage("Xóa người dùng thành công"));
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

    @PostMapping(path = "/courses",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiMessage> createCourse(@Valid @ModelAttribute CourseCreateDTO dto) {

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
    //Gán giảng viên vào khóa học
    @PostMapping("/course-to-instructor")
    public ResponseEntity<ApiMessage> assignInstructorToCourse(@RequestBody InstructorToCourseDTO instructorToCourseDTO){
        try{
        service.assignInstructorToCourse(instructorToCourseDTO);
        return ResponseEntity.ok((new ApiMessage("Gán giảng viên vào khóa học thành công")));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ApiMessage(e.getMessage()));
        }


    }
}
