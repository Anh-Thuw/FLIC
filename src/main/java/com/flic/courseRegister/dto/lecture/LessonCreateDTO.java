package com.flic.courseRegister.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonCreateDTO {
    private String title;
    private String description;
    private Integer week_index;
    private Integer session_index;
    private Long courseId;
    private boolean is_flexible;
}
