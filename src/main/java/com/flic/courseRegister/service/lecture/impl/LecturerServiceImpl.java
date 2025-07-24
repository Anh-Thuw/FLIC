package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.LecturerViewDTO;
import com.flic.courseRegister.entity.InstructorProfile;
import com.flic.courseRegister.mapper.lecture.LecturerMapper;
import com.flic.courseRegister.repository.LecturerRepository;
import com.flic.courseRegister.service.lecture.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService {
    private final LecturerRepository lecturerRepository;
    private final LecturerMapper lecturerMapper;

    @Override
    public LecturerViewDTO getLecturerProfileByEmail(String email) {
        return lecturerRepository.findByUserEmail(email)
                .map(lecturerMapper::toDto)
                .orElseThrow(()-> new RuntimeException("Lecturer not found"));
    }
}
