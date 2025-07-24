package com.flic.courseRegister.service.impl;

import com.flic.courseRegister.dto.CourseAdminViewDTO;
import com.flic.courseRegister.dto.UserAdminViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.mapper.CourseAdminMapper;
import com.flic.courseRegister.mapper.UserAdminMapper;
import com.flic.courseRegister.repository.CourseRepository;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepo;
    private final UserAdminMapper userMapper;
    private final CourseRepository courseRepo;
    private final CourseAdminMapper courseMapper;

//Lấy danh sách người dùng
    @Override
    public Page<UserAdminViewDTO> getAllUsers(Pageable pageable, String status) {
        Page<User> users = (status == null)
                ? userRepo.findAll(pageable)
                : userRepo.findByStatus(status, pageable);

        return users.map(userMapper::toDto);
    }
    @Override
    public List<CourseAdminViewDTO> getAllCourses(String status) {
        List<Course> courses = (status == null)
                ? courseRepo.findAllByOrderByCreatedAtDesc()
                : courseRepo.findByStatus(status);

        return courses.stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }
}

