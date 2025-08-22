package com.flic.courseRegister.dto.user;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CourseProgressDTO {
    private Long enrollmentId;
    private Long courseId;

    // bài tập
    private int totalAssignments;
    private int submittedCount;
    private double assignmentsPercent;   // 0..100

    // điểm danh
    private int totalLessons;
    private int attendedCount;           // present/late/excused
    private double attendancePercent;    // 0..100

    // gộp (theo trọng số)
    private double progressPercent;      // 0..100
}

