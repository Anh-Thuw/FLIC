package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByInstructorEmail(String email);


    // Tìm theo status
    List<Course> findByStatus(String status);

    // Tìm theo isPublished (backup cho status)
    List<Course> findByIsPublished(Boolean isPublished);

    // Sắp xếp theo thời gian tạo
    List<Course> findAllByOrderByCreatedAtDesc();

    // Tìm theo instructor
    List<Course> findByInstructorId(Long instructorId);
}

