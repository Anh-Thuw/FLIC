package com.flic.courseRegister.service.admin.impl;

import com.flic.courseRegister.dto.admin.CourseAdminViewDTO;
import com.flic.courseRegister.dto.admin.CourseCreateDTO;
import com.flic.courseRegister.dto.admin.CourseUpdateDTO;
import com.flic.courseRegister.dto.admin.UserAdminViewDTO;
import com.flic.courseRegister.dto.user.UserCreateDTO;
import com.flic.courseRegister.dto.user.UserUpdateDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.mapper.admin.CourseAdminMapper;
import com.flic.courseRegister.mapper.admin.CourseCreateMapper;
import com.flic.courseRegister.mapper.admin.UserAdminMapper;
import com.flic.courseRegister.mapper.admin.UserCreateMapper;
import com.flic.courseRegister.repository.CourseRepository;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.admin.AdminService;
import jakarta.persistence.EntityNotFoundException;
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
    private final UserCreateMapper userCreateMapper;
    private final CourseRepository courseRepo;
    private final CourseAdminMapper courseMapper;
    private final CourseCreateMapper createMapper;

    //  USER METHODS
    @Override
    public Page<UserAdminViewDTO> getAllUsers(Pageable pageable, String status, String role, String keyword) {
        Page<User> users = userRepo.findWithFilters(keyword, status, role, pageable);
        return users.map(userMapper::toDto);
    }

    @Override
    public Long createUser(UserCreateDTO dto) {
        // Validate email unique
        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        // Validate studentId unique (nếu có)
        if (dto.getStudentId() != null && userRepo.existsByStudentId(dto.getStudentId())) {
            throw new IllegalArgumentException("Mã sinh viên đã tồn tại");
        }

        User user = userCreateMapper.toEntity(dto);
        User saved = userRepo.save(user);
        return saved.getId();
    }

    @Override
    public void updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng"));

        userCreateMapper.updateEntity(user, dto);
        userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new EntityNotFoundException("Không tìm thấy người dùng");
        }
        userRepo.deleteById(id);
    }

    @Override
    public UserAdminViewDTO getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng"));
        return userMapper.toDto(user);
    }

    //  COURSE METHODS
    @Override
    public List<CourseAdminViewDTO> getAllCourses(String status, Course.CourseType type, String startMonth, String keyword) {
        List<Course> courses = courseRepo.findWithFilters(keyword, status, type, startMonth);
        return courses.stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseAdminViewDTO getCourseById(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khóa học"));
        return courseMapper.toDto(course);
    }

    //Tạo khóa học
    @Override
    public Long createCourse(CourseCreateDTO dto) {
        Course course = createMapper.toEntity(dto);
        Course saved = courseRepo.save(course);
        return saved.getId();
    }

    //Update khóa hoc
    @Override
    public void updateCourse(Long id, CourseUpdateDTO dto) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        createMapper.updateEntity(course, dto);
        courseRepo.save(course);
    }

    //Xóa khóa học
    @Override
    public void deleteCourse(Long id) {
        if (!courseRepo.existsById(id))
            throw new EntityNotFoundException("Course not found");
        courseRepo.deleteById(id);
    }

    //    xem ds gv
    @Override
    public Page<UserAdminViewDTO> getAllTeachers(Pageable pageable, String status, String keyword) {
        // Tái sử dụng findWithFilters và cố định role = "lecturer"
        Page<User> teachers = userRepo.findWithFilters(keyword, status, "lecturer", pageable);
        return teachers.map(userMapper::toDto);
    }

}

