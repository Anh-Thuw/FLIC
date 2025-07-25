package com.flic.courseRegister.repository;

import com.flic.courseRegister.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Basic filters
    List<Course> findByStatus(String status);
    List<Course> findByType(Course.CourseType type);
    List<Course> findByStatusAndType(String status, Course.CourseType type);

    // Advanced filters
    List<Course> findByStartMonth(String startMonth);
    List<Course> findByRatingGreaterThanEqual(BigDecimal rating);
    List<Course> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Sorting
    List<Course> findAllByOrderByCreatedAtDesc();
    List<Course> findAllByOrderByRatingDesc();
    List<Course> findAllByOrderByPriceAsc();

    // Search
    @Query("SELECT c FROM Course c WHERE " +
            "(:keyword IS NULL OR c.title LIKE %:keyword% OR c.description LIKE %:keyword%) " +
            "AND (:status IS NULL OR c.status = :status) " +
            "AND (:type IS NULL OR c.type = :type) " +
            "AND (:startMonth IS NULL OR c.startMonth = :startMonth)")
    List<Course> findWithFilters(@Param("keyword") String keyword,
                                 @Param("status") String status,
                                 @Param("type") Course.CourseType type,
                                 @Param("startMonth") String startMonth);
}

