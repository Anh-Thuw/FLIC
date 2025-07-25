package com.flic.courseRegister.dto.lecture;

import com.flic.courseRegister.entity.Attendance.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentAttendanceDTO {
    private String studentId;
    private String fullName;
    private Status status;
}
