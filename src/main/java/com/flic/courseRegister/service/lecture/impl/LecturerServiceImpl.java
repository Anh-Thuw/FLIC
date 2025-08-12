package com.flic.courseRegister.service.lecture.impl;

import com.flic.courseRegister.dto.ImageUploadResult;
import com.flic.courseRegister.dto.lecture.LecturerProfileDTO;
import com.flic.courseRegister.entity.InstructorProfile;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.mapper.lecture.LecturerMapper;
import com.flic.courseRegister.repository.InstructorProfileRepository;
import com.flic.courseRegister.repository.LecturerRepository;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.ImageUploadService;
import com.flic.courseRegister.service.lecture.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService {
    private final LecturerRepository lecturerRepository;
    private final LecturerMapper lecturerMapper;
    private final UserRepository userRepository;
    private final InstructorProfileRepository instructorProfileRepository;
    private final ImageUploadService imageUploadService;

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

    @Transactional(rollbackFor = Exception.class)
    public ImageUploadResult updateLecturerAvatar(Long userId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File ảnh trống hoặc không hợp lệ");
        }

        ImageUploadResult result = imageUploadService.uploadAvatar(file, userId);
        String imageUrl = result.getImageUrl();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setAvatarUrl(imageUrl);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        instructorProfileRepository.findByUserId(userId).ifPresentOrElse(profile -> {
            profile.setProfileImage(imageUrl);
            profile.setUpdatedAt(LocalDateTime.now());
            instructorProfileRepository.save(profile);
        }, () -> {
            InstructorProfile profile = new InstructorProfile();
            profile.setUser(user);
            profile.setProfileImage(imageUrl);
            profile.setCreatedAt(LocalDateTime.now());
            profile.setUpdatedAt(LocalDateTime.now());
            instructorProfileRepository.save(profile);
        });

        return result;
    }
}
