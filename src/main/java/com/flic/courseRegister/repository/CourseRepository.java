package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

import java.util.List;
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllById(Iterable<Long> ids);



    // Tìm theo status
    List<Course> findByStatus(String status);

    // Tìm theo type
    List<Course> findByType(Course.CourseType type);

    // Tìm theo status và type
    List<Course> findByStatusAndType(String status, Course.CourseType type);

    // Sắp xếp theo thời gian tạo
    List<Course> findAllByOrderByCreatedAtDesc();

    // Tìm theo startMonth
    List<Course> findByStartMonth(String startMonth);

    // Tìm theo rating >= giá trị
    List<Course> findByRatingGreaterThanEqual(BigDecimal rating);
}


