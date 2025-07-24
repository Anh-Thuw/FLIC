package com.flic.courseRegister.service.impl;

import com.flic.courseRegister.dto.LecturerViewDTO;
import com.flic.courseRegister.entity.InstructorProfile;
import com.flic.courseRegister.mapper.LecturerMapper;
import com.flic.courseRegister.repository.LecturerRepository;
import com.flic.courseRegister.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService {
    private final LecturerRepository lecturerRepository;
    private final LecturerMapper lecturerMapper;
    @Override
    public LecturerViewDTO getLecturerByUserId(Long userId) {
        InstructorProfile instructorProfile = lecturerRepository.findByUserId(userId).orElseThrow(
                ()-> new RuntimeException("Lecturer not found")
        );
        return lecturerMapper.toDto(instructorProfile);
    }
}
