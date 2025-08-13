package com.flic.courseRegister.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonViewDTO {
    private Long id;
    private Long courseId;
    private String title;
    private String description;
    private Integer week_index;
    private Integer session_index;
}
