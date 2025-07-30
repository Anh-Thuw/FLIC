package com.flic.courseRegister.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class StudentAttendanceUpdateListDTO {
    private Long lessonId;
    private List<StudentsAttendanceUpdateDTO> updates;
}
