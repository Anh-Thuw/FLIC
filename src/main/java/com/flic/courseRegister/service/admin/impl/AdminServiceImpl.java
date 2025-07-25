package com.flic.courseRegister.service.admin.impl;

import com.flic.courseRegister.dto.admin.CourseAdminViewDTO;
import com.flic.courseRegister.dto.admin.CourseCreateDTO;
import com.flic.courseRegister.dto.admin.CourseUpdateDTO;
import com.flic.courseRegister.dto.admin.UserAdminViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.mapper.admin.CourseAdminMapper;
import com.flic.courseRegister.mapper.admin.CourseCreateMapper;
import com.flic.courseRegister.mapper.admin.UserAdminMapper;
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
    private final CourseRepository courseRepo;
    private final CourseAdminMapper courseMapper;
    private final CourseCreateMapper createMapper;

    //Lấy danh sách người dùng
    @Override
    public Page<UserAdminViewDTO> getAllUsers(Pageable pageable, String status) {
        Page<User> users = (status == null)
                ? userRepo.findAll(pageable)
                : userRepo.findByStatus(status, pageable);

        return users.map(userMapper::toDto);
    }

    //Lấy danh sách khóa học
    @Override
    public List<CourseAdminViewDTO> getAllCourses(String status, Course.CourseType type) {
        List<Course> courses;

        if (status != null && type != null) {
            courses = courseRepo.findByStatusAndType(status, type);
        } else if (status != null) {
            courses = courseRepo.findByStatus(status);
        } else if (type != null) {
            courses = courseRepo.findByType(type);
        } else {
            courses = courseRepo.findAllByOrderByCreatedAtDesc();
        }

        return courses.stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
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
}

