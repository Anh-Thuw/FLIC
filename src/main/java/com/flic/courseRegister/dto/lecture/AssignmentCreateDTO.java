package com.flic.courseRegister.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentCreateDTO {
    private Long lessonId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private String fileUrl;
}
