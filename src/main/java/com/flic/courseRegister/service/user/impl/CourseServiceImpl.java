package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.user.CourseDetailResponse;
import com.flic.courseRegister.dto.user.CourseResponse;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.exception.ResourceNotFoundException;
import com.flic.courseRegister.mapper.user.CourseMapper;
import com.flic.courseRegister.repository.CourseRepository;
import com.flic.courseRegister.service.user.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseResponse> getAllActiveCourses() {
        List<Course> courses =  courseRepository.findByStatus("active"); // hoac ACTIVE
        return courseMapper.toDtoList(courses);
    }

    @Override
    public CourseDetailResponse getCourseDetailById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy khóa học với id = " + id
                ));

        return courseMapper.toDetailDto(course);
    }
}
