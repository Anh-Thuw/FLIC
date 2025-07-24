package com.flic.courseRegister.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseAdminViewDTO {
    private Long id;
    private String title;
    private String status;
}
