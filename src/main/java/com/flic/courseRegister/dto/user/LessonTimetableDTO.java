package com.flic.courseRegister.dto.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonTimetableDTO {
    private String courseTitle;
    private String lecturerName;
    private String weekIndex;
    private int sessionIndex;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
}
