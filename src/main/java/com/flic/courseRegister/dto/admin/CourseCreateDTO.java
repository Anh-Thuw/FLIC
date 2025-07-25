package com.flic.courseRegister.dto.admin;

import com.flic.courseRegister.entity.Course;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/* request khi tạo khoá học */
@Data
public class CourseCreateDTO {
    @NotBlank
    private String title;

    private String description;

    @DecimalMin(value = "0.0")
    private BigDecimal price;

    @Min(1)
    private Integer duration;  // phút

    private String image;

    private String startMonth;  // VD: "2025-08", "2025-12"

    @NotNull
    private Course.CourseType type;  // short_course hoặc cert_exam
}

