package com.flic.courseRegister.dto.lecture;

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
    private String title;
    private String description;
    private String thumbnailUrl;
    private String level;
    private String slug;
    private BigDecimal price;
    private Boolean isPublished;
    private String status;
    public String getStatusFromPublished() {
        if (isPublished == null) return "draft";
        return isPublished ? "active" : "inactive";
    }
}
