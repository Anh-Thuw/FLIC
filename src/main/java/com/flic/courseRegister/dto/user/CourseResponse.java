package com.flic.courseRegister.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String title;
    private BigDecimal rating;
    private String description;
    private BigDecimal price;
    private String status;
    private String imageUrl;
    private String startMonth;
    private String type;
}

