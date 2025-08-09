package com.flic.courseRegister.service.user.impl;

import com.flic.courseRegister.dto.ImageUploadResult;
import com.flic.courseRegister.entity.User;
import com.flic.courseRegister.repository.UserRepository;
import com.flic.courseRegister.service.ImageUploadService;
import com.flic.courseRegister.service.user.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final ImageUploadService imageUploadService;
    private final UserRepository userRepository;

    @Override
    public ImageUploadResult updateAvatar(Long userId, MultipartFile file) {
        // Upload avatar (overwrite cũ)
        String folder = "profile";
        String publicIdPrefix = "user" + userId;

        ImageUploadResult result = imageUploadService.uploadAvatar(file, userId);

        // Update DB
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setAvatarUrl(result.getImageUrl());
        userRepository.save(user);

        return result;
    }
}

