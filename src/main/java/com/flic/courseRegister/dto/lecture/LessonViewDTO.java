package com.flic.courseRegister.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonViewDTO {
    private Long id;
    private Long courseId;
    private String title;
    private String description;
    private Integer weekIndex;
    private LocalDateTime plannedAt;
    private LocalDateTime endTime;
}
