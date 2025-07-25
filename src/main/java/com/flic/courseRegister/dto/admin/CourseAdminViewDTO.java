package com.flic.courseRegister.dto.admin;

import com.flic.courseRegister.entity.Course;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @Builder
public class CourseAdminViewDTO {
    private Long id;
    private String title;
    private String status;
    private BigDecimal price;
    private BigDecimal rating;
    private Integer duration;
    private String startMonth;
    private Course.CourseType type;
    private LocalDateTime createdAt;
}
