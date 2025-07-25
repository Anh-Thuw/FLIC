package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.CourseLecturerViewDTO;
import com.flic.courseRegister.entity.Course;
import com.flic.courseRegister.mapper.lecture.CourseLecturerMapper;
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
    @Override
    public List<CourseLecturerViewDTO> getCourseByLecturerEmail(String email) {
        List<Course> courses = courseRepository.findByInstructorEmail(email);
        return courses.stream()
                .map(courseLecturerMapper::toDto)
                .toList();
    }
}
