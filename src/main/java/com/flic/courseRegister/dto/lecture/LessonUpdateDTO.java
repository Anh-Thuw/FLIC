package com.flic.courseRegister.dto.lecture;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonUpdateDTO {
   private String title;
   private String description;
   private Integer weekIndex ;
   @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
   private LocalDateTime plannedAt;
   @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
   private LocalDateTime endTime;
}
