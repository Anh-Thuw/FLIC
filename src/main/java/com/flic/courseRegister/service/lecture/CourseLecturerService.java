package com.flic.courseRegister.service.lecture;

import com.flic.courseRegister.dto.lecture.CourseLecturerViewDTO;

import java.util.List;

public interface CourseLecturerService {
    List<CourseLecturerViewDTO> getCourseByLecturerEmail(String email);
}
