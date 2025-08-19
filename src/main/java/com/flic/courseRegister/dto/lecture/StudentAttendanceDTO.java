package com.flic.courseRegister.dto.lecture;

import com.flic.courseRegister.entity.Attendance.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentAttendanceDTO {
    private LocalDate birthDate;
    private String fullName;
    private Status status;
}
