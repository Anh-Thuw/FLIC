package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.lecture.LecturerProfileDTO;
import com.flic.courseRegister.entity.InstructorProfile;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.mapper.lecture.LecturerMapper;
import com.flic.courseRegister.repository.LecturerRepository;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.lecture.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService {
    private final LecturerRepository lecturerRepository;
    private final LecturerMapper lecturerMapper;
    private final UserRepository userRepository;

    @Override
    public LecturerProfileDTO getLecturerProfileByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        InstructorProfile lecturer = lecturerRepository.findByUserEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên"));
        return lecturerMapper.toDto(lecturer);
    }

    @Override
    public LecturerProfileDTO updateLecturerProfile(String email, LecturerProfileDTO lecturerProfileDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        InstructorProfile lecturer = lecturerRepository.findByUserEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên"));
        lecturerMapper.updateLecturerFromDto(lecturerProfileDTO,lecturer);
        lecturerRepository.save(lecturer);
        return lecturerMapper.toDto(lecturer);
    }
}
