package com.flic.courseRegister.dto.admin;

import lombok.Data;

import java.math.BigDecimal;

/* request khi cập nhật khoá học */
@Data
public class CourseUpdateDTO {
    private String title;
    private String description;
    private BigDecimal price;
    private Integer duration;
    private String level;
    private Boolean isPublished;        // chuyển trạng thái
}
