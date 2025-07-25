package com.flic.courseRegister.dto.admin;

import com.flic.courseRegister.entity.Course;
import lombok.Data;

import java.math.BigDecimal;

/* request khi cập nhật khoá học */
@Data
public class CourseUpdateDTO {
    private String title;
    private String description;
    private BigDecimal price;
    private Integer duration;
    private String status;  // "active", "inactive", "draft"
    private String image;
    private String startMonth;
    private Course.CourseType type;
    private BigDecimal rating;  // Admin có thể cập nhật rating
}

