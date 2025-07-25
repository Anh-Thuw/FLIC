package com.flic.courseRegister.service.admin;

import com.flic.courseRegister.dto.admin.CourseAdminViewDTO;
import com.flic.courseRegister.dto.admin.CourseCreateDTO;
import com.flic.courseRegister.dto.admin.CourseUpdateDTO;
import com.flic.courseRegister.dto.admin.UserAdminViewDTO;
import com.flic.courseRegister.dto.user.UserCreateDTO;
import com.flic.courseRegister.dto.user.UserUpdateDTO;
import com.flic.courseRegister.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
//    Page<UserAdminViewDTO> getAllUsers(Pageable pageable, String status);

    List<CourseAdminViewDTO> getAllCourses(String status, Course.CourseType type, String startMonth, String keyword);
    CourseAdminViewDTO getCourseById(Long id);
    Long createCourse(CourseCreateDTO dto);
    void updateCourse(Long id, CourseUpdateDTO dto);
    void deleteCourse(Long id);

    // Updated user methods
    Page<UserAdminViewDTO> getAllUsers(Pageable pageable, String status, String role, String keyword);
    Long createUser(UserCreateDTO dto);
    void updateUser(Long id, UserUpdateDTO dto);
    void deleteUser(Long id);
    UserAdminViewDTO getUserById(Long id);
}

