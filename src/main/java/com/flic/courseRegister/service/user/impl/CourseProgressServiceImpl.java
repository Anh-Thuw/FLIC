package com.flic.courseRegister.service.user.impl;
// service/impl/CourseProgressServiceImpl.java
import com.flic.courseRegister.dto.user.CourseProgressDTO;
import com.flic.courseRegister.repository.EnrollmentRepository;
import com.flic.courseRegister.repository.ProgressRow;
import com.flic.courseRegister.service.user.CourseProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service @RequiredArgsConstructor
public class CourseProgressServiceImpl implements CourseProgressService {

    private final EnrollmentRepository enrollmentRepo;

    private CourseProgressDTO map(ProgressRow r, double wA, double wAtt) {
        double assignPct = Optional.ofNullable(r.getAssignmentsPercent()).orElse(0.0);
        double attendPct = Optional.ofNullable(r.getAttendancePercent()).orElse(0.0);
        double combined  = Math.round((assignPct * wA + attendPct * wAtt) * 100.0) / 100.0;

        return CourseProgressDTO.builder()
                .enrollmentId(r.getEnrollmentId())
                .courseId(r.getCourseId())
                .totalAssignments(Objects.requireNonNullElse(r.getTotalAssignments(), 0))
                .submittedCount(Objects.requireNonNullElse(r.getSubmittedCount(), 0))
                .assignmentsPercent(assignPct)
                .totalLessons(Objects.requireNonNullElse(r.getTotalLessons(), 0))
                .attendedCount(Objects.requireNonNullElse(r.getAttendedCount(), 0))
                .attendancePercent(attendPct)
                .progressPercent(combined)
                .build();
    }

    @Override
    public List<CourseProgressDTO> getMyCoursesProgress(Long userId, double wAssignment, double wAttendance) {
        double sum = (wAssignment + wAttendance);
        if (sum <= 0) { wAssignment = 1; wAttendance = 0; sum = 1; }
        double wA = wAssignment / sum, wAtt = wAttendance / sum;

        var rows = enrollmentRepo.fetchMyCoursesProgress(userId);
        var out = new ArrayList<CourseProgressDTO>(rows.size());
        for (var r : rows) out.add(map(r, wA, wAtt));
        return out;
    }

    @Override
    public CourseProgressDTO getMyCourseProgress(Long userId, Long courseId, double wAssignment, double wAttendance) {
        double sum = (wAssignment + wAttendance);
        if (sum <= 0) { wAssignment = 1; wAttendance = 0; sum = 1; }
        double wA = wAssignment / sum, wAtt = wAttendance / sum;

        var r = enrollmentRepo.fetchMyProgressForCourse(userId, courseId);
        if (r == null) return null;
        return map(r, wA, wAtt);
    }
}

