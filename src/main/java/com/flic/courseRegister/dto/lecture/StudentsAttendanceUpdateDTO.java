package com.flic.courseRegister.dto.lecture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.flic.courseRegister.entity.Attendance.Status;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentsAttendanceUpdateDTO {
    private Long attendanceId;
    private Status status;
}
