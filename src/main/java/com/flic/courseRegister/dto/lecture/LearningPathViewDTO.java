package com.flic.courseRegister.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LearningPathViewDTO {
    private Long courseId;
    private Integer week;
    private Integer session;
    private String title;
}
