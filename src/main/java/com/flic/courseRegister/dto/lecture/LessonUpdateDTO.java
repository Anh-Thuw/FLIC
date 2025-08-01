package com.flic.courseRegister.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonUpdateDTO {
   private Long lessonId;
   private Long lecturerId;
   private String updateTitle;
   private String updateDescription;
   private String note;
}
