package com.flic.courseRegister.dto.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AssignmentViewDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private String fileUrl;
}

