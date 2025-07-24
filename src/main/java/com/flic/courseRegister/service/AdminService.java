package com.flic.courseRegister.service;

import com.flic.courseRegister.dto.CourseAdminViewDTO;
import com.flic.courseRegister.dto.UserAdminViewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
    Page<UserAdminViewDTO> getAllUsers(Pageable pageable, String status);
    List<CourseAdminViewDTO> getAllCourses(String status);

}
