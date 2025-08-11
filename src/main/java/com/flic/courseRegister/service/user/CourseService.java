package com.flic.courseRegister.service.user;

import com.flic.courseRegister.dto.user.CourseDetailResponse;
import com.flic.courseRegister.dto.user.CourseResponse;
import java.util.List;

public interface CourseService {
    List<CourseResponse> getAllActiveCourses();
    CourseDetailResponse getCourseDetailById(Long id);

    List<CourseDetailResponse> getCourseByUserEmail(String email);
}

