package com.flic.courseRegister.dto.user;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseDetailResponse {
    private Long id;
    private String title;
    private String description;
    private BigDecimal rating;
    private BigDecimal price;
    private Integer duration;
    private String status;
    private String imageUrl;
    private String startMonth;
    private String type;
}

