package com.flic.courseRegister.repository;

public interface ProgressRow {
    Long getEnrollmentId();
    Long getCourseId();

    Integer getTotalAssignments();
    Integer getSubmittedCount();
    Double  getAssignmentsPercent();

    Integer getTotalLessons();
    Integer getAttendedCount();
    Double  getAttendancePercent();
}

