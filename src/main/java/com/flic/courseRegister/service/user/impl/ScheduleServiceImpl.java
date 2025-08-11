package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.user.ScheduleViewDTO;
import com.flic.courseRegister.entity.Enrollment;
import com.flic.courseRegister.mapper.user.ScheduleMapper;
import com.flic.courseRegister.repository.CourseRepository;
import com.flic.courseRegister.repository.EnrollmentRepository;
import com.flic.courseRegister.service.user.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleMapper scheduleMapper;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    @Override
    public List<ScheduleViewDTO> getSchedule(String email) {
        List<Enrollment> enrollmentsList = enrollmentRepository.findByUserEmail(email);

        return enrollmentsList.stream()
                .map(enrollment -> scheduleMapper.toDto(enrollment.getUser(), enrollment.getCourse()))
                .toList();
    }
}
