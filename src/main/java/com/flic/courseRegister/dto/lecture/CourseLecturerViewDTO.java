package com.flic.courseRegister.dto.lecture;

import com.flic.courseRegister.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseLecturerViewDTO {
    private Long id;
    private String lecturerInCharge;
    private String title;
    private String description;
    private BigDecimal rating;
    private BigDecimal price;
    private Integer duration;
    private String status;
    private String image;
}
