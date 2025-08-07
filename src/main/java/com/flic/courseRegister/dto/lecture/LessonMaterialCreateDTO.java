package com.flic.courseRegister.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonMaterialCreateDTO {
    private Long lessonId;
    private Long revisionId; // có thể null nếu chưa có revision
    private String title;
    private String fileUrl;
}
