package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.user.CourseDetailResponse;
import com.flic.courseRegister.dto.user.CourseProgressResponseDTO;
import com.flic.courseRegister.dto.user.CourseResponse;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.CourseInstructor;
import com.flic.courseRegister.entity.Enrollment;
import com.flic.courseRegister.exception.ResourceNotFoundException;
import com.flic.courseRegister.mapper.user.CourseMapper;
import com.flic.courseRegister.repository.CourseInstructorRepository;
import com.flic.courseRegister.repository.CourseRepository;
import com.flic.courseRegister.repository.EnrollmentRepository;
import com.flic.courseRegister.service.user.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseInstructorRepository courseInstructorRepository;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper, EnrollmentRepository enrollmentRepository, CourseInstructorRepository courseInstructorRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.enrollmentRepository = enrollmentRepository;
        this.courseInstructorRepository = courseInstructorRepository;
    }

    @Override
    public List<CourseResponse> getAllActiveCourses() {
        List<Course> courses = courseRepository.findByStatus("active"); // hoac ACTIVE
        return courseMapper.toDtoList(courses);
    }

    @Override
    public CourseDetailResponse getCourseDetailById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy khóa học với id = " + id
                ));
        CourseInstructor courseInstructor = courseInstructorRepository.findByCourseId(id);

        return courseMapper.toDetailDto(course, courseInstructor);
    }

    @Override
    public List<CourseProgressResponseDTO> getCourseByUserEmail(String email) {
        List<Enrollment> enrollmentList = enrollmentRepository.findByUserEmail(email);
        return enrollmentList.stream()
                .map(enrollment -> {
                    Course course = enrollment.getCourse();
                    CourseInstructor courseInstructor = courseInstructorRepository.findByCourseId(course.getId());
                    CourseDetailResponse courseDetail = courseMapper.toDetailDto(course, courseInstructor);
                    return new CourseProgressResponseDTO(courseDetail, enrollment.getProgress());
                })
                .toList();
    }

}
