package com.flic.courseRegister.service.user;

import com.flic.courseRegister.dto.ImageUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface UserProfileService {
    ImageUploadResult updateAvatar(Long userId, MultipartFile file);
}

