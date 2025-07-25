package com.flic.courseRegister.service.admin;

import com.flic.courseRegister.dto.admin.CourseAdminViewDTO;
import com.flic.courseRegister.dto.admin.CourseCreateDTO;
import com.flic.courseRegister.dto.admin.CourseUpdateDTO;
import com.flic.courseRegister.dto.admin.UserAdminViewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
    Page<UserAdminViewDTO> getAllUsers(Pageable pageable, String status);
    List<CourseAdminViewDTO> getAllCourses(String status);
    Long createCourse(CourseCreateDTO dto);
    void updateCourse(Long id, CourseUpdateDTO dto);
    void deleteCourse(Long id);
}
