package com.flic.courseRegister.dto.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

/* request khi tạo khoá học */
@Data
public class CourseCreateDTO {
    @NotBlank
    private String title;
    private String description;
    private BigDecimal price;
    private Integer duration;           // phút
    private String level;               // beginner / intermediate / advanced
}
