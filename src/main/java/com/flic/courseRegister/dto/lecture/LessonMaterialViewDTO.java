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
public class LessonMaterialViewDTO {
    private Long id;
    private String title;
    private String fileUrl;
    private String type;
    private LocalDateTime uploadedAt;
}
