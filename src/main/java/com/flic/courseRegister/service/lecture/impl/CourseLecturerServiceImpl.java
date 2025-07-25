package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.CourseLecturerViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.entity.CourseInstructor;
import com.flic.courseRegister.mapper.lecture.CourseLecturerMapper;
import com.flic.courseRegister.repository.CourseInstructorRepository;
import com.flic.courseRegister.repository.CourseRepository;
import com.flic.courseRegister.service.lecture.CourseLecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseLecturerServiceImpl implements CourseLecturerService {
    private final CourseRepository courseRepository;
    private final CourseLecturerMapper courseLecturerMapper;
    private final CourseInstructorRepository courseInstructorRepository;
    @Override
    public List<CourseLecturerViewDTO> getCourseByLecturerEmail(String email) {
        List<CourseInstructor> instructorsList = courseInstructorRepository.findByUserEmail(email);
        List<Long> courseIds = instructorsList.stream()
                .map(ci -> ci.getCourse().getId())
                .distinct()
                .toList();
        List<Course> courses = courseRepository.findAllById(courseIds);
        return instructorsList.stream()
                .map(instructor -> courseLecturerMapper.toDto(
                        instructor.getCourse(),
                        instructor.getUser()
                ))
                .toList();
    }
}
